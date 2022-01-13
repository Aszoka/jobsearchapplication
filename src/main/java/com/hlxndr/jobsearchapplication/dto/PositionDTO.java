package com.hlxndr.jobsearchapplication.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PositionDTO {

    @Size(min = 4, max = 50, message = "Required length is between 4 and 50 characters")
    private String jobTitle;
    @Size(min = 4, max = 50, message = "Required length is between 4 and 50 characters")
    private String location;
}
