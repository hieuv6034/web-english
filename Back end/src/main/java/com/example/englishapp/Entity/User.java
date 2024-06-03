package com.example.englishapp.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.Instant;
import java.util.Set;

@Table
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userID;

    @Column(name = "avt")
    @Lob
    private String avt;

    @Column(name = "email", length = 55)
    @NotBlank
    private String Email;

    @Column(name = "username", length = 16)
    @NotBlank
    private String Username;

    @Column(name = "password")
    @NotBlank
    private String Password;

    @Column(name = "role")
    @NotBlank
    private String Role;

    @Column(name = "isBanned")
    private boolean isBanned;

    @Column(name = "Point")
    private int point;

    public User(String username, String email, String password) {
        Email = email;
        Username = username;
        Password = password;
        Role = "ROLE_USER";
        isBanned = false;
        point = 0;
        avt = "https://www.google.com/url?sa=i&url=https%3A%2F%2Fm.tinnhanhchungkhoan.vn%2Fcho-shiba-gay-sot-voi-dang-ngoi-xem-tivi-giong-het-con-nguoi-post138746.html&psig=AOvVaw3PS2xPZR3Ega1xBq5ywCJN&ust=1652952740338000&source=images&cd=vfe&ved=0CAwQjRxqFwoTCPCy1Zzf6PcCFQAAAAAdAAAAABAO";
    }
}
