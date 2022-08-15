package com.example.project_assessment.dto;

import com.example.project_assessment.model.ReadingList;
import lombok.Data;
import org.w3c.dom.stylesheets.LinkStyle;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class UserResponseDTO {
    private int id;
    private String username;
    private String password;
}
