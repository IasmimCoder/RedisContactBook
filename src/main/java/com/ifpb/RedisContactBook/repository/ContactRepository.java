package com.ifpb.RedisContactBook.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.ifpb.RedisContactBook.model.Contact;

@Repository
public interface ContactRepository extends CrudRepository<Contact, String> {
}