package com.ifpb.RedisContactBook.service;

import com.ifpb.RedisContactBook.exceptions.NotFoundException;
import com.ifpb.RedisContactBook.model.Contact;
import com.ifpb.RedisContactBook.repository.ContactRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    private static final Logger logger = LoggerFactory.getLogger(ContactService.class);

    public Contact save (Contact contact) {
        if(!validarTelefone(contact.getNumber())){
            contact.setNumber("Inválido");
        }
        if(!verificarDDD(contact.getDdd())){
            contact.setDdd("Inválido");
        }
        return contactRepository.save(contact);
    }

    public Contact update(String id, Contact contact){
        Optional<Contact> contactOptional = contactRepository.findById(id);
        if (!contactOptional.isPresent())
            throw new NotFoundException("Contato não encontrado!");
        Contact var = contactOptional.get();

        if(!validarTelefone(contact.getNumber())){
            contact.setNumber("Inválido");
        }
        if(!verificarDDD(contact.getDdd())){
            contact.setDdd("Inválido");
        }

        var.setName(contact.getName());
        var.setDdd(contact.getDdd());
        var.setNumber(contact.getNumber());
        var.setSecondNumber(contact.getSecondNumber());
        var.setEmail(contact.getEmail());

        return contactRepository.save(var);
    }

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

    public void deleteById(String id) {
       Optional<Contact> contactOptional = contactRepository.findById(id);
       if (!contactOptional.isPresent())
           throw new NotFoundException("Contato não encontrado!");
        contactRepository.deleteById(id);
    }

    public void deleteAll(){
        contactRepository.deleteAll();
    }

    private static boolean validarTelefone(String telefone){
        //retira todos os caracteres não-numéricos (incluindo espaço,tab, etc)
       telefone = telefone.replaceAll("\\D","");

        if (telefone.startsWith("0800")) {

            //verifica se tem a qtde de numeros correta
            if (telefone.length() == 11){
                return true;
            }
            return false;
        }

        //verifica se tem a qtde de numeros correta
        if (!(telefone.length() >= 8 && telefone.length() <= 10)) return false;

       //verifica se o numero foi digitado com todos os dígitos iguais
       java.util.regex.Pattern p = java.util.regex.Pattern.compile(telefone.charAt(0)+"{"+telefone.length()+"}");
       java.util.regex.Matcher m = p.matcher(telefone);
       if(m.find()) return false;

       return true;
    }

    public static boolean verificarDDD(String ddd){
        
        String[] DDDs = {
                "11", "12", "13", "14", "15", "16", "17", "18", "19",
                "21", "22", "24", "27", "28", "31", "32", "33", "34",
                "35", "37", "38", "41", "42", "43", "44", "45", "46",
                "47", "48", "49", "51", "53", "54", "55", "61", "62",
                "64", "63", "65", "66", "67", "68", "69", "71", "73",
                "74", "75", "77", "79", "81", "82", "83", "84", "85",
                "86", "87", "88", "89", "91", "92", "93", "94", "95",
                "96", "97", "98", "99"};

        for(String d: DDDs){
            if(ddd.equals(d)){
                return true;
            }
        }
        return false;
    }

}
