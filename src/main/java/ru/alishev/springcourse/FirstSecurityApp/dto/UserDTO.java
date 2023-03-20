package ru.alishev.springcourse.FirstSecurityApp.dto;

import lombok.Data;

import javax.validation.constraints.*;

/**
 * @author Neil Alishev
 */
@Data
public class UserDTO {
    @NotEmpty(message = "Имя не должно быть пустым")
    @Size(min = 2, max = 100, message = "Имя должно быть от 2 до 100 символов длиной")
    private String username;

    @Email
    private String email;

    @NotNull
    @NotEmpty
    private String password;

}
