package ru.alishev.springcourse.FirstSecurityApp.Auth.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@RequiredArgsConstructor
public class Token {

    private int id;

    private String email;

    private String username;

    private String token;



    public Token(int id, String email, String username, String token) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.token = token;
    }
}
