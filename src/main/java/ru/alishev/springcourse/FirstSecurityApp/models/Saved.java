package ru.alishev.springcourse.FirstSecurityApp.models;

import lombok.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.*;

@Table(name = "Saved")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Saved {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id")
   private int id;

   @Column(name = "user_id")
   private int userId;

   @Column(name = "field")
   private String field;

   @Column(name = "score")
   private int scores;

   @Column(name = "rows")
   private int rows;

   @Column(name = "cols")
   private int cols;

   @Column(name = "level")
   private int level;
}
