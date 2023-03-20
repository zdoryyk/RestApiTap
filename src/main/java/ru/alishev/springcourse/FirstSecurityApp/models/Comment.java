package ru.alishev.springcourse.FirstSecurityApp.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;
@ToString
@Getter
@Setter
@Entity
@Table(name = "Comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int comment_id;

    @Column(name = "text")
    @NotNull
    private String comment;

    @Column(name = "commented_on")
    @NotNull
    private LocalDateTime commentedOn;


    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    public Comment(int comment_id, String comment, LocalDateTime commentedOn) {
        this.comment_id = comment_id;
        this.comment = comment;
        this.commentedOn = commentedOn;
    }

    public Comment() {

    }
}
