package ru.test.task.customercontacts;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.test.task.customercontacts.model.ContactDto;
import ru.test.task.customercontacts.model.ContactType;
import ru.test.task.customercontacts.model.CustomerBriefDto;
import ru.test.task.customercontacts.model.CustomerDto;
import ru.test.task.customercontacts.persistence.Contact;
import ru.test.task.customercontacts.persistence.ContactRepository;
import ru.test.task.customercontacts.persistence.Customer;
import ru.test.task.customercontacts.persistence.CustomerRepository;

import java.util.EnumSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
@AllArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final ContactRepository contactRepository;
    private final CustomerMapper customerMapper;
    private final ContactMapper contactMapper;

    private static void checkCustomer(CustomerDto customer) {
        if (customer == null || !StringUtils.hasText(customer.getName())) {
            throw new IllegalArgumentException("customer name wasn't provided");
        }
    }

    private static void checkContact(ContactDto contact) {
        if (contact == null || contact.getContactType() == null
            || contact.getContactType().negate().test(contact.getContactValue())) {
            throw new IllegalArgumentException("contact isn't valid");
        }
    }

    private static void checkCustomerId(long customerId) {
        if (customerId <= 0) {
            throw new IllegalArgumentException("incorrect customer id");
        }
    }

    private static NoSuchElementException customerNotFoundExceptionSupplier() {
        return new NoSuchElementException("customer not found");
    }

    public CustomerDto createCustomer(CustomerDto customer) {
        checkCustomer(customer);
        Customer customerCandidate = new Customer();
        customerCandidate.setName(customer.getName());
        Customer savedCustomer = customerRepository.save(customerCandidate);
        return customerMapper.map(savedCustomer, 0L);
    }

    public ContactDto addContact(long customerId, ContactDto contact) {
        checkCustomerId(customerId);
        checkContact(contact);
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(CustomerService::customerNotFoundExceptionSupplier);
        Contact contactCandidate = contactMapper.createContact(contact, customer);
        Contact savedContact = contactRepository.save(contactCandidate);
        return contactMapper.map(savedContact);
    }

    public Page<CustomerBriefDto> getCustomers(Integer pageNumber, int pageSize) {
        int size = pageSize > 0 ? pageSize : Integer.MAX_VALUE;
        int number = pageNumber == null || pageNumber < 0 ? 0 : pageNumber;
        Pageable pageable = PageRequest.of(number, size, Sort.Direction.ASC, "id");
        Page<Customer> customers = customerRepository.findAll(pageable);
        return customers.map(customerMapper::mapBrief);
    }

    public CustomerDto getCustomer(long customerId) {
        checkCustomerId(customerId);
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(CustomerService::customerNotFoundExceptionSupplier);
        long contactsCount = contactRepository.countContactsByCustomerId(customer.getId());
        return customerMapper.map(customer, contactsCount);
    }

    public List<ContactDto> getContacts(long customerId, ContactType type) {
        checkCustomerId(customerId);
        if (!customerRepository.existsById(customerId)) {
            throw customerNotFoundExceptionSupplier();
        }
        Set<ContactType> typesFilter = type == null ? ContactType.all() : EnumSet.of(type);
        List<Contact> contacts = contactRepository
                .findAllByCustomerIdAndContactTypeIn(customerId, typesFilter, Sort.by("id"));
        return contacts.stream()
                .map(contactMapper::map)
                .toList();
    }
}
