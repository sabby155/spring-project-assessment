package com.example.project_assessment.dto;

import com.example.project_assessment.model.Author;
import com.example.project_assessment.model.Genre;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

@Data
public class CreateBookDTO {
    private String title;
    private String author;
    private List<String> genres;
    private int pages;
    private Date published;
}
