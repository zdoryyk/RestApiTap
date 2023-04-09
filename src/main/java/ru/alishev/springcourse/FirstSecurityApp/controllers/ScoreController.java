package ru.alishev.springcourse.FirstSecurityApp.controllers;


import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.alishev.springcourse.FirstSecurityApp.dto.ScoreDTO;
import ru.alishev.springcourse.FirstSecurityApp.dto.UserDTO;
import ru.alishev.springcourse.FirstSecurityApp.models.Rating;
import ru.alishev.springcourse.FirstSecurityApp.models.Score;
import ru.alishev.springcourse.FirstSecurityApp.models.User;
import ru.alishev.springcourse.FirstSecurityApp.services.RatingService;
import ru.alishev.springcourse.FirstSecurityApp.services.ScoreService;
import ru.alishev.springcourse.FirstSecurityApp.services.UserService;
import ru.alishev.springcourse.FirstSecurityApp.util.Response;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/scores")
@CrossOrigin(origins = "http://localhost:4200")
public class ScoreController {


    final ScoreService scoreService;
    final UserService userService;
    final RatingService ratingService;
    final ModelMapper modelMapper;


    @PostMapping("/add-score")
    public ResponseEntity<?> addScores(@RequestBody ScoreDTO scoreDTO){
        System.out.println("USER " + scoreDTO);

        User user = userService.getUserById(scoreDTO.getUser_id());
        if(user == null){
            return ResponseEntity.badRequest().body(new Response(HttpStatus.NOT_FOUND,"NOT_FOUND"));
        }
        try {
            Score score = convertToScore(scoreDTO);
            score.setUser(user);
            scoreService.addScore(score);
            user.setScores(scoreService.getScoreByUserId(user.getId()));
            userService.saveUser(user);
            ratingService.addRating(user,score);
        }catch (Error e){
            return ResponseEntity.badRequest().body(new Response(HttpStatus.CONFLICT, e.getMessage()));
        }

        return ResponseEntity.ok(new Response(HttpStatus.ACCEPTED,"ACCEPTED"));
    }


    @GetMapping("/sort-score")
    public List<ScoreDTO> getSortedScores(){
        return scoreService.getSortedScores();
    }


    @PostMapping("/user-score")
    public List<Score> getUserScore(@RequestBody UserDTO userDTO){
        return scoreService.getScoreByUserId(userDTO.getId());
    }


    @GetMapping
    public List<Score> getAllScores(){
        return scoreService.getAllScores();
    }

    private Score convertToScore(ScoreDTO scoreDTO){
        return modelMapper.map(scoreDTO, Score.class);
    }

    private ScoreDTO convertToScoreDTO(Score score){
        return modelMapper.map(score, ScoreDTO.class);
    }

}
