package com.hlxndr.jobsearchapplication.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ClientApp {

    @Id
    private String email;
    @Column(length = 100)
    private String name;
}
