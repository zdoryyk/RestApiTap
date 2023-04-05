package ru.alishev.springcourse.FirstSecurityApp.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.alishev.springcourse.FirstSecurityApp.models.Score;
import ru.alishev.springcourse.FirstSecurityApp.repositories.ScoreRepository;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Service
public class ScoreService {

    final ScoreRepository scoreRepository;

    public void addScore(Score score){
          score.setPlayedOn(LocalDateTime.now());
          scoreRepository.save(score);
    }

    public List<Score> getAllScores(){
        return scoreRepository.findAll();
    }

    public List<Score> getScoreByUserId(int id){
        return scoreRepository.getScoreByUser_Id(id);
    }

}
