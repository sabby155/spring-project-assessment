package com.example.project_assessment.dto;

import com.example.project_assessment.model.User;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Data
public class CreateReadingListDTO {
    @NotBlank
    private String name;
}
