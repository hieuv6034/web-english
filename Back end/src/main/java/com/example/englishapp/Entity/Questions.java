package com.example.englishapp.Entity;


import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Table
@Entity
@Data
public class Questions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @Column(name = "Question")
    @Lob
    private String Question;

    @Column(name = "Answer")
    @Lob
    private String Answer1;

    @Column(name = "Answer1")
    @Lob
    private String Answer2;

    @Column(name = "Answer2")
    @Lob
    private String Answer3;

    @Column(name = "Answer3")
    @Lob
    private String Answer4;

    @Column(name = "AnswerCorrect")
    @Lob
    private String AnswerCorrect;

    @ManyToOne
    @JoinColumn(name="Lesson_id")
    private Lesson lesson;
}
