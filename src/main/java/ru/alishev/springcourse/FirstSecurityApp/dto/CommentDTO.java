package ru.alishev.springcourse.FirstSecurityApp.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class CommentDTO {

    @NotEmpty
    @NotNull
    private String text;

    @NotNull
    private int id;


}
