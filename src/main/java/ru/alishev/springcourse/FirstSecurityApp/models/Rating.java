package ru.alishev.springcourse.FirstSecurityApp.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "Rating")
public class Rating {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rating_id")
    private int rating_id;

    @Column(name = "points")
    @NotNull
    private int rating;

    @Column(name = "rated_on")
    @NotNull
    private LocalDateTime ratedOn;

    @OneToOne()
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;


    public Rating(int rating_id, int rating, LocalDateTime ratedOn) {
        this.rating_id = rating_id;
        this.rating = rating;
        this.ratedOn = ratedOn;
    }

    public Rating() {
    }
}
