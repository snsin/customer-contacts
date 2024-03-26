package ru.test.task.customercontacts;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.test.task.customercontacts.model.ContactDto;
import ru.test.task.customercontacts.model.ContactType;
import ru.test.task.customercontacts.model.CustomerBriefDto;
import ru.test.task.customercontacts.model.CustomerDto;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/customers", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Slf4j
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("")
    public CustomerDto createCustomer(@RequestBody @Valid @NotNull CustomerDto customer) {
        CustomerDto createdCustomer = customerService.createCustomer(customer);
        log.info("created customer with id = {}", createdCustomer.getId());
        return createdCustomer;

    }

    @PostMapping("/{customerId}/contacts")
    public ContactDto addContact(@PathVariable long customerId, @RequestBody @Valid @NotNull ContactDto contact) {
        ContactDto createdContact = customerService.addContact(customerId, contact);
        log.info("created contact with id = {} for customer with id = {}", createdContact.getId(), customerId);
        return createdContact;
    }

    @GetMapping("")
    public Page<CustomerBriefDto> getCustomers(@RequestParam(required = false) Integer pageNumber,
                                               @RequestParam(required = false) Integer pageSize) {
        Page<CustomerBriefDto> customers = customerService.getCustomers(pageNumber, pageSize == null ? -1 : pageSize);
        log.info("retrieved {} customer(s)", customers.getTotalElements());
        return customers;
    }

    @GetMapping("/{customerId}")
    public CustomerDto getCustomer(@PathVariable long customerId) {
        CustomerDto customer = customerService.getCustomer(customerId);
        log.info("retrieved customer with id = {}, which has {} contact(s)",
                customer.getId(), customer.getContactsCount());
        return customer;
    }

    @GetMapping("/{customerId}/contacts")
    public List<ContactDto> getCustomerContacts(@PathVariable long customerId,
                                                @RequestParam(required = false) ContactType type) {
        List<ContactDto> contacts = customerService.getContacts(customerId, type);
        log.info("retrieved {} contact(s) for customer with id = {}", contacts.size(), customerId);
        return contacts;
    }
}
