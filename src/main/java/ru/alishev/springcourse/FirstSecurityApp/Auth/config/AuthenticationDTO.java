package ru.alishev.springcourse.FirstSecurityApp.Auth.config;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * @author Neil Alishev
 */
@Data
public class AuthenticationDTO {
    @Email
    private String email;

    private String password;

}
