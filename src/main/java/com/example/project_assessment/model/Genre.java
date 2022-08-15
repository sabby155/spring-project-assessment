package com.example.project_assessment.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Genre")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Genre {
    @Id
    @GeneratedValue
    private int id;
    @NotBlank
    private String name;

    @ManyToMany
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Set<Book> setOfBooks = new HashSet<>();
}
