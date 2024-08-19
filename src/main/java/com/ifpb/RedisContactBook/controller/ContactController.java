package com.ifpb.RedisContactBook.controller;

import com.ifpb.RedisContactBook.model.Contact;
import com.ifpb.RedisContactBook.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.interceptor.SimpleKey;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@RestController
@RequestMapping("/contacts")
public class ContactController {

    @Autowired
    private ContactService contactService;


    @PostMapping
    public ResponseEntity<Contact> createContact(@RequestBody Contact contact) {
        return ResponseEntity.ok(contactService.save(contact));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contact> getContact(@PathVariable String id) {
        contactService.checkCache(id);
        Contact contact = contactService.findById(id);
        return ResponseEntity.ok(contact);
    }

    @GetMapping
    public ResponseEntity<Iterable<Contact>> getAllContacts() {
        contactService.checkCache(SimpleKey.EMPTY.toString()); // Verifica se o cache foi utilizado
        Iterable<Contact> contacts = contactService.findAll();
        return ResponseEntity.ok(contacts);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contact> updateContact(@PathVariable String id, @RequestBody Contact contact) {
        return ResponseEntity.ok(contactService.update(id, contact));
    }

    @DeleteMapping("/{id}")
    public void deleteContact(@PathVariable String id) {
        contactService.deleteById(id);
    }

    @DeleteMapping()
    public void deleteAll(){
        contactService.deleteAll();
    }
}
