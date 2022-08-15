package com.example.project_assessment.repository;

import com.example.project_assessment.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
//    https://bushansirgur.in/spring-data-jpa-finder-methods-by-field-name-with-examples/
    Author findByName(String name);
}
