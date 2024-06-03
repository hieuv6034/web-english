package com.example.englishapp.Entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "Comments")
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotBlank
    @Lob
    private String Content;

    @Column
    private String username;

    @ManyToOne
    @JoinColumn(name="Lesson_id", nullable=false)
    private Lesson lesson;
}
