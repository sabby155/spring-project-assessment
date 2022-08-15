package com.example.project_assessment.dto;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Data
public class CreateAuthorDTO {
    @Id
    @GeneratedValue
    private int id;
    @NotBlank
    public String name;
}
