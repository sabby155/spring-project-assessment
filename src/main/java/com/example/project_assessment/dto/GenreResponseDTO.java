package com.example.project_assessment.dto;

import com.example.project_assessment.model.Book;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Data
public class GenreResponseDTO {
    private int id;
    private String name;
//    private Set<Book> setOfBooks;
}
