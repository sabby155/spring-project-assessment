package com.example.project_assessment.repository;

import com.example.project_assessment.model.Book;
import com.example.project_assessment.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {
}
