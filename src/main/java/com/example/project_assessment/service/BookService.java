package com.example.project_assessment.service;

import com.example.project_assessment.dto.BookResponseDTO;
import com.example.project_assessment.dto.CreateBookDTO;
import com.example.project_assessment.exception.NotFoundException;
import com.example.project_assessment.model.Author;
import com.example.project_assessment.model.Book;
import com.example.project_assessment.model.Genre;
import com.example.project_assessment.repository.AuthorRepository;
import com.example.project_assessment.repository.BookRepository;
import com.example.project_assessment.repository.GenreRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class BookService {
    @Autowired
    private BookRepository repository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private GenreService genreService;

    public List<BookResponseDTO> getAll() {
        List<Book> books = repository.findAll();
        List<BookResponseDTO> bookResponseList = new ArrayList<>();

         books.forEach(book -> {
            BookResponseDTO bookResponseDTO = modelMapper.map(book, BookResponseDTO.class);
            bookResponseDTO.setAuthor(book.getAuthor().getName());
            bookResponseDTO.setGenres(book.getGenreList().stream().map(genre -> genre.getName()).toList());
            bookResponseList.add(bookResponseDTO);
        });

        return bookResponseList;
    }


//    {
//        "title": "Newest Book Title",
//        "pages": 100,
//        "genres": ["genre", "anotherGenre", "anotherSuperMegaGenre"],
//        "author": "Author Name",
//        "published": "2022-05-10"
//    }
    //Create a new book
    public BookResponseDTO create(CreateBookDTO createBookDTO) {

        Book book = modelMapper.map(createBookDTO, Book.class);
        //Set book's title
        book.setPages(createBookDTO.getPages());
        //Set books pages
        book.setTitle(createBookDTO.getTitle());

        //Check for an existing  author
        Author author = authorRepository.findByName(createBookDTO.getAuthor());
        //If not found create a new one and set/save
        if (author == null) {
            author = new Author();
            author.setName(createBookDTO.getAuthor());
            authorRepository.save(author);
        }
        //Set this author as my book-objects author
        book.setAuthor(author);

        //create a list of genres to create book or set existing genre
        List<Genre> listOfGenres = new ArrayList<>();

        for (String singleGenre: createBookDTO.getGenres()) {

            // If already exists in repo add this book to its list of books.
            Genre genre = genreRepository.findByName(singleGenre);
            if (genre != null) {
                genre.getSetOfBooks().add(book);
            } else {
                //If genre name doesn't exist, create new genre via set/save
                genre = new Genre();
                genre.setName(singleGenre);
                //add this book to its list of books.
                genre.getSetOfBooks().add(book);
                genreRepository.save(genre);
            }
            //Add this genre to my genre list initialized above
            listOfGenres.add(genre);
        }
        //Set my book objects genrelist to this new list
        book.setGenreList(listOfGenres);

        //Save to book repo to create/return a book
        repository.save(book);


       BookResponseDTO bookResponseDTO = modelMapper.map(book, BookResponseDTO.class);
       bookResponseDTO.setAuthor(book.getAuthor().getName());
       bookResponseDTO.setGenres(book.getGenreList().stream().map(Genre::getName).toList());
       return bookResponseDTO;

    }

    //delete a book by its ID - redo
    public void deleteById(int id) {
        if(repository.existsById(id)) {
            repository.deleteById(id);
        }
        else {
            throw new NotFoundException("Book not found");
        }
    }

    //Get a book by its ID - this works
    public BookResponseDTO getById(int id) {
        Book book = repository.findById(id).orElseThrow(() -> new NotFoundException("Book not found"));
        BookResponseDTO bookResponseDTO = modelMapper.map(book,BookResponseDTO.class);
        bookResponseDTO.setAuthor(book.getAuthor().getName());
        bookResponseDTO.setGenres(book.getGenreList().stream().map(genre -> genre.getName()).toList());
        return bookResponseDTO;
    }

    public BookResponseDTO updateBookById(int id, CreateBookDTO newBookDetails) {
        Book book = repository.findById(id).orElseThrow(() -> new NotFoundException("Book not found"));

        book.setTitle(newBookDetails.getTitle());
        book.setPublished(newBookDetails.getPublished());
        book.setPages(newBookDetails.getPages());

        //Set new author detail on
        Author author = authorRepository.findByName(newBookDetails.getAuthor());
        if (author == null) {
            author = new Author();
            author.setName(newBookDetails.getAuthor());
            authorRepository.save(author);
        }
        book.setAuthor(author);


        //Set new genre details on book

        List<Genre> listOfGenres = new ArrayList<>();

        for (String singleGenre: newBookDetails.getGenres()) {
            Genre genre = genreRepository.findByName(singleGenre);
            if (genre != null) {
                genre.getSetOfBooks().add(book);
            } else {
                genre = new Genre();
                genre.setName(singleGenre);
                genre.getSetOfBooks().add(book);
                genreRepository.save(genre);
            }
            listOfGenres.add(genre);
        }
        book.setGenreList(listOfGenres);
        repository.save(book);

        BookResponseDTO bookResponseDTO = modelMapper.map(book, BookResponseDTO.class);
        bookResponseDTO.setAuthor(book.getAuthor().getName());
        bookResponseDTO.setGenres(book.getGenreList().stream().map(Genre::getName).toList());
        bookResponseDTO.setPublished(book.getPublished());
        bookResponseDTO.setPages(book.getPages());
        bookResponseDTO.setTitle(book.getTitle());

        return bookResponseDTO;
    }
}
