package com.example.englishapp.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Entity
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @Column(name = "time_open")
    private Instant timeOpen;

    @Column(name = "time_close")
    private int timeclose;

    @Column(name = "Username")
    private String username;

    @Column(name = "Point")
    private Double point;

    @Column(name = "time")
    private Instant time;
}
