package com.hlxndr.jobsearchapplication.model;

import lombok.*;
import org.springframework.web.bind.annotation.PathVariable;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ClientApp {

    @Id
    //@Email(regexp = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$", message = "Email must be in valid format")
    private String email;
    @Column(length = 100)
    //@Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
    private String name;
    private UUID ownApiKey;

    public ClientApp(String email, String name) {
        this.email = email;
        this.name = name;
    }
}
