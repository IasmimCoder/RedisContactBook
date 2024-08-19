package com.ifpb.RedisContactBook.controller;

import com.ifpb.RedisContactBook.model.Contact;
import com.ifpb.RedisContactBook.service.ContactService;
import com.ifpb.RedisContactBook.utilities.CheckCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @Autowired
    private CheckCache checkCache;

    @PostMapping
    public ResponseEntity<Contact> createContact(@RequestBody Contact contact) {
        return ResponseEntity.ok(contactService.save(contact));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contact> getContact(@PathVariable String id) {
        checkCache.checkCacheForId(id); // Verifica se o cache foi utilizado, passando a chave como parâmetro
        return ResponseEntity.ok(contactService.findById(id));
    }

    @GetMapping
    public ResponseEntity<Iterable<Contact>> getAllContacts() {
        checkCache.checkCacheForAll(); // Verifica se o cache foi utilizado
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