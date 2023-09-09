package ru.test.task.customercontacts;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import ru.test.task.customercontacts.model.ContactDto;
import ru.test.task.customercontacts.persistence.Contact;
import ru.test.task.customercontacts.persistence.Customer;

@Mapper(unmappedTargetPolicy = ReportingPolicy.WARN, componentModel = "spring")
public interface ContactMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "contactType", source = "contactType")
    @Mapping(target = "contactValue", source = "contactValue")
    ContactDto map(Contact contact);

    @Mapping(target = "contactType", source = "contact.contactType")
    @Mapping(target = "contactValue", source = "contact.contactValue")
    @Mapping(target = "customer", source = "customer")
    Contact createContact(ContactDto contact, Customer customer);
}
