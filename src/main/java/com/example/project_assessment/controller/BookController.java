package com.example.project_assessment.controller;

import com.example.project_assessment.dto.BookResponseDTO;
import com.example.project_assessment.dto.CreateBookDTO;
import com.example.project_assessment.dto.CreateUserDTO;
import com.example.project_assessment.dto.UserResponseDTO;
import com.example.project_assessment.service.BookService;
import com.example.project_assessment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/books")
public class BookController {
    @Autowired
    private BookService bookService;

    //Get all books
    @GetMapping
    public List<BookResponseDTO> getAllBooks() {
        return bookService.getAll();
    }

    //Get a specific book by ID
    @GetMapping("/{id}")
    public BookResponseDTO getBookByID(@PathVariable int id) {
        return bookService.getById(id);
    }

    //Create a book
    @PostMapping
    public BookResponseDTO createBook(@Valid @RequestBody CreateBookDTO createBookDTO) {
        return bookService.create(createBookDTO);
    }

    //Update a book with the given ID
    @PutMapping("/{id}")
    public BookResponseDTO updateBook(@PathVariable int id, @Valid @RequestBody CreateBookDTO newBook) {
        return bookService.updateBookById(id, newBook);
    }

    //Delete a Book
    @DeleteMapping("/{id}")
    public void deleteBookById(@PathVariable int id) {
        bookService.deleteById(id);
    }
}
