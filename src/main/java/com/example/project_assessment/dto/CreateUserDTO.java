package com.example.project_assessment.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CreateUserDTO {
    private int id;
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
