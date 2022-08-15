package com.example.project_assessment.service;

import com.example.project_assessment.dto.AuthorResponseDTO;
import com.example.project_assessment.dto.CreateAuthorDTO;
import com.example.project_assessment.model.Author;
import com.example.project_assessment.repository.AuthorRepository;
import com.example.project_assessment.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AuthorService {
    @Autowired
    private AuthorRepository repository;

    @Autowired
    private ModelMapper modelMapper;
    public AuthorResponseDTO create(CreateAuthorDTO createAuthorDTO) {
        if (!repository.existsById(createAuthorDTO.getId())) {
            Author author = new Author();
            author.setName(createAuthorDTO.getName());
            repository.save(author);
            AuthorResponseDTO authorResponseDTO = new AuthorResponseDTO();
            authorResponseDTO.setName(author.getName());
            return authorResponseDTO;
        } else {
            return modelMapper.map(repository.existsById(createAuthorDTO.getId()), AuthorResponseDTO.class);
        }
    }

//    public Author getAuthor(CreateAuthorDTO createAuthorDTO) {
//        //Check for existing authors
//        Author author = new Author();
//        author.setName(createAuthorDTO.name);
//        log.info(String.valueOf(createAuthorDTO));
//        return author;
//    }

}
