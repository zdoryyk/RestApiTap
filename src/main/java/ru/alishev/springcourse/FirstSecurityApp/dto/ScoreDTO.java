package ru.alishev.springcourse.FirstSecurityApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScoreDTO {

    private int user_id;
    private String username;
    private int points;

    public ScoreDTO(String username, int points) {
        this.username = username;
        this.points = points;
    }
}
