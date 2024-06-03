package com.example.englishapp.Dto;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
public class UserDTO {

    private Long userID;

    private String avt;

    private String Username;

    private String Role;

    private boolean isBanned;

    private int point;
}
