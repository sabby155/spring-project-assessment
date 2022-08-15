package com.example.project_assessment.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ReadingList")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class ReadingList {

    @Id
    @GeneratedValue
    private int id;
    @NotBlank
    @NotNull
    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @ManyToMany(mappedBy = "readingLists")
    private List<Book> listOfBooks = new ArrayList<>();
}
