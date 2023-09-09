package ru.test.task.customercontacts.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ContactDto {
    private Long id;
    @NotNull
    private ContactType contactType;
    @NotBlank
    private String contactValue;
}
