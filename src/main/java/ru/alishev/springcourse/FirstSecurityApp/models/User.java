package ru.alishev.springcourse.FirstSecurityApp.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Neil Alishev
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "Users")
public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Имя не должно быть пустым")
    @Size(min = 2, max = 100, message = "Имя должно быть от 2 до 100 символов длиной")
    @Column(name = "name")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    @Email
    private String email;

    @Column(name = "role")
    private String role;

    @Column(name = "created_on")
    private LocalDateTime created_on;

    @Column(name = "updated_on")
    private LocalDateTime updated_on;

    @Column(name = "photo_url")
    private String photoUrl;

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Comment> commentList;

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Score> scores;

    @OneToOne(mappedBy = "user",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JsonManagedReference
    private Rating rating;


    public User(int id, String username, String password, String email, String role, LocalDateTime created_on, LocalDateTime updated_on, String photoUrl) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.created_on = created_on;
        this.updated_on = updated_on;
        this.photoUrl = photoUrl;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", created_on=" + created_on +
                ", updated_on=" + updated_on +
                ", photo_url='" + photoUrl + '\'' +
                ", commentList=" + commentList +
                ", scores=" + scores +
                ", rating=" + rating +
                '}';
    }
}