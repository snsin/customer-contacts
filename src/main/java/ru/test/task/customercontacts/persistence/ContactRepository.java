package ru.test.task.customercontacts.persistence;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.test.task.customercontacts.model.ContactType;

import java.util.List;
import java.util.Set;

public interface ContactRepository extends JpaRepository<Contact, Long> {

    List<Contact> findAllByCustomerIdAndContactTypeIn(long id, Set<ContactType> types, Sort sort);

    long countContactsByCustomerId(long id);

}