package com.example.englishapp.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatusData {
    private String status;
    private String message;
    private String englishmean;
    private String vietnamesemean;
    private String type;
}
