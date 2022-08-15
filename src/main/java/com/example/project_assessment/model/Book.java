package com.example.project_assessment.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "Book")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Book {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @NotBlank
    private String title;

    @Min(1)
    private int pages;

    @Temporal(TemporalType.DATE)
    private Date published;

    @ManyToMany
    private List<ReadingList> readingLists = new ArrayList<>();

    @ManyToMany(mappedBy = "setOfBooks")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Genre> genreList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "author_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Author author;
}
