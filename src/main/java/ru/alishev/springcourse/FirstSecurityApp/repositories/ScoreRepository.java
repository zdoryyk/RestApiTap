package ru.alishev.springcourse.FirstSecurityApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.alishev.springcourse.FirstSecurityApp.models.Score;

import java.util.List;

@Repository
public interface ScoreRepository extends JpaRepository<Score,Integer> {

    List<Score> getScoreByUser_Id(int id);

    @Query("SELECT u.username, r.points FROM User u JOIN u.scores r ORDER BY r.points DESC")
    List<Object[]> getUsersByScores();
}
