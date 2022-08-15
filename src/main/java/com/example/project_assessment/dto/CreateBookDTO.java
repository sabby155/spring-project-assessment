package com.example.project_assessment.dto;


import lombok.Data;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

@Data
public class CreateBookDTO {
    @NotBlank
    private String title;
    @NotBlank
    private String author;
    private List<String> genres;
    @Min(1)
    private int pages;
    private Date published;
}
