package com.example.project_assessment.repository;

import com.example.project_assessment.model.ReadingList;
import com.example.project_assessment.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReadingListRepository extends JpaRepository<ReadingList, Integer> {
    List<ReadingList> findByUser(User user);
}
