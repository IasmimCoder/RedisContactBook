package com.ifpb.RedisContactBook.service;

import com.ifpb.RedisContactBook.exceptions.NotFoundException;
import com.ifpb.RedisContactBook.model.Contact;
import com.ifpb.RedisContactBook.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;


    public Contact save (Contact contact) {
        return contactRepository.save(contact);
    }

    public Contact update(String id, Contact contact){
        Optional<Contact> contactOptional = contactRepository.findById(id);
        if (!contactOptional.isPresent())
            throw new NotFoundException("Contato não encontrado!");
        Contact var = contactOptional.get();

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

    public Iterable<Contact> findAll() {
        return contactRepository.findAll();
    }

    public void deleteById(String id) {
       Optional<Contact> contactOptional = contactRepository.findById(id);
       if (!contactOptional.isPresent())
           throw new NotFoundException("Contato não encontrado!");
        contactRepository.deleteById(id);
    }

}
