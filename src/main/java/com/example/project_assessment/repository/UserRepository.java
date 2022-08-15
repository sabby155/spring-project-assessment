package com.example.project_assessment.repository;
import com.example.project_assessment.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
