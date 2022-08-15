package com.example.project_assessment.dto;

import com.example.project_assessment.model.Author;
import com.example.project_assessment.model.Genre;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
public class BookResponseDTO {
    private int id;
    private String title;
    private String author;
    private List<String> genres;
    private int pages;
    private Date published;
}
