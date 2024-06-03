package com.example.englishapp.Dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class levelDto {
    private String name;

    private String pic;

    private List<String> NameLesson = new ArrayList<>();

    private List<Long> id = new ArrayList<>();
}
