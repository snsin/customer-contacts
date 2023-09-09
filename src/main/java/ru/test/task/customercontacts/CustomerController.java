package ru.test.task.customercontacts;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
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
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("")
    public CustomerDto createCustomer(@RequestBody @Valid @NotNull CustomerDto customer) {
        return customerService.createCustomer(customer);

    }

    @PostMapping("/{customerId}/contacts")
    public ContactDto addContact(@PathVariable long customerId, @RequestBody @Valid @NotNull ContactDto contact) {
        return customerService.addContact(customerId, contact);
    }

    @GetMapping("")
    public Page<CustomerBriefDto> getCustomers(@RequestParam(required = false) Integer pageNumber,
                                               @RequestParam(required = false) Integer pageSize) {
        return customerService.getCustomers(pageNumber, pageSize == null ? -1 : pageSize);
    }

    @GetMapping("/{customerId}")
    public CustomerDto getCustomer(@PathVariable long customerId) {
        return customerService.getCustomer(customerId);
    }

    @GetMapping("/{customerId}/contacts")
    public List<ContactDto> getCustomerContacts(@PathVariable long customerId,
                                                @RequestParam(required = false) ContactType type) {
        return customerService.getContacts(customerId, type);
    }
}
