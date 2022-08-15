package com.example.project_assessment.repository;

import com.example.project_assessment.model.Genre;
import com.example.project_assessment.model.ReadingList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Integer> {
    Genre findByName(String name);
}
