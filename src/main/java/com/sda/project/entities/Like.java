package com.sda.project.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.Period;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String name;

    @Email
    @NotBlank
    @Column(unique = true)
    private String email;

    @NotBlank
    private String country;

    @NotNull
    private LocalDate dateOfBirth;

    public Like(String name, String email, String country, LocalDate dateOfBirth) {
        this.name = name;
        this.email = email;
        this.country = country;
        this.dateOfBirth = dateOfBirth;
    }

    public Like(String name, String email, String country) {
        this(name, email, country, LocalDate.now());
    }

    @JsonIgnore
    public int getAge() {
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }
}
