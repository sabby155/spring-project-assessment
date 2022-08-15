package com.example.project_assessment.controller;

import com.example.project_assessment.dto.BookResponseDTO;
import com.example.project_assessment.dto.GenreResponseDTO;
import com.example.project_assessment.dto.UserResponseDTO;
import com.example.project_assessment.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/genre")
public class GenreController {
    @Autowired
    private GenreService genreService;

    @GetMapping
    public List<GenreResponseDTO> getAllGenres() {
        return genreService.getAll();
    }

    //Get all books in the genre with the given ID.
    @GetMapping("/{id}/books")
    public List<BookResponseDTO> getBooksByGenreID(@PathVariable int id) {
        return genreService.getBooksByGenreID(id);
    }
}
