package ru.test.task.customercontacts;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import ru.test.task.customercontacts.model.CustomerBriefDto;
import ru.test.task.customercontacts.model.CustomerDto;
import ru.test.task.customercontacts.persistence.Customer;

@Mapper(unmappedTargetPolicy = ReportingPolicy.WARN, componentModel = "spring")
public interface CustomerMapper {
    @Mapping(target = "id", source = "customer.id")
    @Mapping(target = "name", source = "customer.name")
    @Mapping(target = "contactsCount", source = "contactsCount")
    CustomerDto map(Customer customer, long contactsCount);

    @Mapping(target = "id", source = "customer.id")
    @Mapping(target = "name", source = "customer.name")
    CustomerBriefDto mapBrief(Customer customer);
}
