package com.example.englishapp.Dto;

import lombok.Data;


@Data
public class QuestionDTO {
    private Long ID;

    private String Question;

    private String Answer1;

    private String Answer2;

    private String Answer3;

    private String Answer4;

    private String ClientAnswer;
}
