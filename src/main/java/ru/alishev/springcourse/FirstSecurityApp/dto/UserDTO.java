package ru.alishev.springcourse.FirstSecurityApp.dto;

import lombok.Data;

import javax.validation.constraints.*;

/**
 * @author Neil Alishev
 */
@Data
public class UserDTO {


    private int id;

    @Size(min = 2, max = 100, message = "Имя должно быть от 2 до 100 символов длиной")
    private String username;

    @Email
    private String email;

    private String password;

}
