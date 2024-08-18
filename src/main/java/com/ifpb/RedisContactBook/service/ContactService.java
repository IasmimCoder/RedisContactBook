package com.ifpb.RedisContactBook.service;

import com.ifpb.RedisContactBook.exceptions.NotFoundException;
import com.ifpb.RedisContactBook.model.Contact;
import com.ifpb.RedisContactBook.repository.ContactRepository;

import com.ifpb.RedisContactBook.utilities.Validations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.interceptor.SimpleKey;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    private static final Logger logger = LoggerFactory.getLogger(ContactService.class);
    private final CacheManager cacheManager;

    @Autowired
    public ContactService(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    public void checkCache() {
        var cache = cacheManager.getCache("contacts");
        var value = cache.get(SimpleKey.EMPTY); //O SimpleKey.EMPTY é utilizado para métodos sem parâmetros
        if (value != null) {
            logger.info("Usando cache para findAll");
        } else {
            logger.info("Nenhum cache encontrado para findAll");
        }
    }

    @CacheEvict(value = "contacts", allEntries = true)
    @CachePut(value = "contacts")
    public Contact save (Contact contact) {
        if(!Validations.validarTelefone(contact.getNumber())){
            contact.setNumber("Inválido");
        }
        if(!Validations.validarDDD(contact.getDdd())){
            contact.setDdd("Inválido");
        }
        return contactRepository.save(contact);
    }

    @CacheEvict(value = "contacts", allEntries = true)
    @CachePut(value = "contacts")
    public Contact update(String id, Contact contact){
        Optional<Contact> contactOptional = contactRepository.findById(id);
        if (!contactOptional.isPresent())
            throw new NotFoundException("Contato não encontrado!");
        Contact var = contactOptional.get();

        if(!Validations.validarTelefone(contact.getNumber())){
            contact.setNumber("Inválido");
        }
        if(!Validations.validarDDD(contact.getDdd())){
            contact.setDdd("Inválido");
        }

        var.setName(contact.getName());
        var.setDdd(contact.getDdd());
        var.setNumber(contact.getNumber());
        var.setSecondNumber(contact.getSecondNumber());
        var.setEmail(contact.getEmail());

        return contactRepository.save(var);
    }

    @Cacheable(value = "contacts", key = "#id")
    public Contact findById(String id) {
        Optional<Contact> contactOptional = contactRepository.findById(id);
        if (!contactOptional.isPresent())
            throw new NotFoundException("Contato não encontrado!");
        return contactOptional.get();
    }

    @Cacheable(value = "contacts")
    public Iterable<Contact> findAll() {
        // System.out.println("Método findAll() chamado.");
        logger.info("Método findAll chamado - buscando contatos do banco de dados");
        return contactRepository.findAll();
    }

    @CacheEvict(value = "contacts", allEntries = true)
    @CachePut(value = "contacts")
    public void deleteById(String id) {
       Optional<Contact> contactOptional = contactRepository.findById(id);
       if (!contactOptional.isPresent())
           throw new NotFoundException("Contato não encontrado!");
        contactRepository.deleteById(id);
    }

    @CacheEvict(value = "contacts", allEntries = true)
    public void deleteAll(){
        contactRepository.deleteAll();
    }
}