package com.papook.usergod.model;

import java.time.LocalDate;

import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "first_name")
    @Setter(AccessLevel.NONE)
    private String firstName;

    @Column(name = "last_name")
    @Setter(AccessLevel.NONE)
    private String lastName;

    @Email
    private String email;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @JsonbTransient
    private String password;

    public void setFirstName(String firstName) {
        // Capitalize the first letter of the first name
        this.firstName = firstName.substring(0, 1).toUpperCase()
                + firstName.substring(1);
    }

    public void setLastName(String lastName) {
        // Capitalize the first letter of the last name
        this.lastName = lastName.substring(0, 1).toUpperCase()
                + lastName.substring(1);
    }

}
