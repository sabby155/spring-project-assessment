package com.example.project_assessment.service;

import com.example.project_assessment.dto.BookResponseDTO;
import com.example.project_assessment.dto.GenreResponseDTO;
import com.example.project_assessment.exception.NotFoundException;
import com.example.project_assessment.model.Book;
import com.example.project_assessment.model.Genre;
import com.example.project_assessment.repository.BookRepository;
import com.example.project_assessment.repository.GenreRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class GenreService {
    @Autowired
    private GenreRepository repository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ModelMapper modelMapper;


    public List<GenreResponseDTO> getAll() {
        return repository.findAll().stream().map(genre -> modelMapper.map(genre, GenreResponseDTO.class)).toList();
    }


    public List<BookResponseDTO> getBooksByGenreID(int id) {
        Genre genre = repository.findById(id).orElseThrow(() -> new NotFoundException("Genre not found"));

        List<BookResponseDTO> listOfBookResponses = new ArrayList<>();
        Set<Book> books = genre.getSetOfBooks();

        books.forEach(book -> {
            BookResponseDTO bookResponseDTO = modelMapper.map(book, BookResponseDTO.class);
            bookResponseDTO.setAuthor(book.getAuthor().getName());
            bookResponseDTO.setGenres(book.getGenreList().stream().map(genreName -> genreName.getName()).toList());
            listOfBookResponses.add(bookResponseDTO);
        });

        return listOfBookResponses;
    }
}
