package ru.alishev.springcourse.FirstSecurityApp.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "Score")
public class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "score_id")
    private int score_id;

    @Column(name = "points")
    @NotNull
    private Integer points;


    @Column(name = "played_on")
    @NotNull
    private LocalDateTime playedOn;


    @ManyToOne()
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;


    public Score(int score_id, Integer points, LocalDateTime playedOn) {
        this.score_id = score_id;
        this.points = points;
        this.playedOn = playedOn;
    }

    public Score() {
    }

    @Override
    public String toString() {
        return "Score{" +
                "score_id=" + score_id +
                ", points=" + points +
                ", playedOn=" + playedOn +
                ", user=" + user +
                '}';
    }
}
