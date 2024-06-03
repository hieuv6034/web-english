package com.example.englishapp.Dto;

import lombok.Data;

import java.util.List;

@Data
public class LessonDTO {
    private Long LessonID;

    private String Name;

    private String English_text;

    private String level;

    private String view;
}
