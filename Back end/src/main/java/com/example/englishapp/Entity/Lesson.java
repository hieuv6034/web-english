package com.example.englishapp.Entity;

import lombok.Data;

import javax.persistence.*;
import java.time.Instant;
import java.util.Set;

@Table
@Entity
@Data
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "Name", unique = true, length = 20)
    private String Name;

    @Column(name = "Content")
    @Lob
    private String Content;

    @OneToMany(mappedBy = "lesson", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Comment> comments;

    @ManyToOne
    @JoinColumn(name="Level_id", nullable=false)
    private Level level;

    @OneToMany(mappedBy = "lesson", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Questions> questions;
}
