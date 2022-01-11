package com.hlxndr.jobsearchapplication.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50)
    @Size(min = 4, max = 50, message = "Required length is between 4 and 50 characters")
    private String jobTitle;
    @Size(min = 4, max = 50, message = "Required length is between 4 and 50 characters")
    @Column(length = 50)
    private String location;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    private ClientApp postedBy;

    public Position(String jobTitle, String location) {
        this.jobTitle = jobTitle;
        this.location = location;
    }

    public Position(String jobTitle, String location, ClientApp postedBy) {
        this.jobTitle = jobTitle;
        this.location = location;
        this.postedBy = postedBy;
    }
}
