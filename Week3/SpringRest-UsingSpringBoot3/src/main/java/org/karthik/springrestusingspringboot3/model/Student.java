package org.karthik.springrestusingspringboot3.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Student {
    @Id
    private int id;

    @NotBlank(message = "name should not be null")
    private String name;
    @Email(message = "Email in proper format")
    private String email;
}
