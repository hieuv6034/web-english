package com.example.englishapp.Entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "token_payment")
@Data
public class TokenPayment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    @NotBlank
    private String username;

    @Column(name = "token")
    @NotBlank
    private String token;

    public TokenPayment(String username, String token) {
        this.username = username;
        this.token = token;
    }

    public TokenPayment() {

    }
}
