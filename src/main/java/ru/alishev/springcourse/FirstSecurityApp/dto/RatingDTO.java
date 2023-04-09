package ru.alishev.springcourse.FirstSecurityApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RatingDTO {

    private String username;
    private int points;

}
