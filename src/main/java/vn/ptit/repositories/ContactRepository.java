package vn.ptit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.ptit.entities.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> { }
