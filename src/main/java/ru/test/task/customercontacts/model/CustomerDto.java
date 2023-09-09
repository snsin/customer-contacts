package ru.test.task.customercontacts.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CustomerDto {

    private long id;
    @NotBlank
    private String name;
    private long contactsCount;

}
