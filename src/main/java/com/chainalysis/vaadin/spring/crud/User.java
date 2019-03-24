package com.chainalysis.vaadin.spring.crud;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Alejandro Duarte
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @NotNull
    private Integer id;

    @NotNull
    private String name;

    @Past
    private LocalDate birthDate;

    @NotNull
    private int phoneNumber; // as an int for testing purposes

    @NotNull
    @Email
    private String email;

    @NotNull
    @Size(min = 6, max = 100)
    private String password;

    private Boolean active = true;

    private Group mainGroup;

    private Set<Group> groups = new HashSet<>();

    private MaritalStatus maritalStatus;
}
