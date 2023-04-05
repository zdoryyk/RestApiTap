package ru.alishev.springcourse.FirstSecurityApp.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.alishev.springcourse.FirstSecurityApp.models.Rating;
import ru.alishev.springcourse.FirstSecurityApp.models.Score;
import ru.alishev.springcourse.FirstSecurityApp.models.User;
import ru.alishev.springcourse.FirstSecurityApp.repositories.RatingRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.RecursiveTask;

@AllArgsConstructor
@Service
public class RatingService {

    final RatingRepository repository;
    final UserService userService;

    public List<Rating> getAllRating(){
        return repository.findAll();
    }

    public Rating getRatingByUserId(int id){
        return repository.getRatingByUser_Id(id);
    }

    public void addRating(Rating rating){
        repository.save(rating);
    }
    public void addRating(User user, Score score){
        Rating rating = new Rating();

        if(repository.getRatingByUser_Id(user.getId()) == null){
            rating.setRating(score.getPoints());
            rating.setUser(user);
            rating.setRatedOn(LocalDateTime.now());
        }else{
            rating = repository.getRatingByUser_Id(user.getId());
            rating.setRating(rating.getRating() + score.getPoints());
            rating.setUser(user);
            rating.setRatedOn(LocalDateTime.now());
        }
        addRating(rating);
        user.setRating(rating);
        userService.saveUser(user);
    }

}
