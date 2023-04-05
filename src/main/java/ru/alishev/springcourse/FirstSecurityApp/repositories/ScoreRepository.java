package ru.alishev.springcourse.FirstSecurityApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.alishev.springcourse.FirstSecurityApp.models.Score;

import java.util.List;

@Repository
public interface ScoreRepository extends JpaRepository<Score,Integer> {

    List<Score> getScoreByUser_Id(int id);

}
