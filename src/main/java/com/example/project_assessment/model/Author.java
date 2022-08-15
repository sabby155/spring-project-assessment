package com.example.project_assessment.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Author")
@Getter
@Setter
//@EntityListeners(AuditingEntityListener.class)
public class Author {
    @Id
    @GeneratedValue
    private int id;
    @NotBlank
    private String name;

    @OneToMany(mappedBy = "author")
    private Set<Book> setOfBooks = new HashSet<>();

}
