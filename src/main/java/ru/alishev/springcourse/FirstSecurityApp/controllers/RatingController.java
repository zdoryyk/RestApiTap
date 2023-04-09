package ru.alishev.springcourse.FirstSecurityApp.controllers;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.alishev.springcourse.FirstSecurityApp.dto.RatingDTO;
import ru.alishev.springcourse.FirstSecurityApp.models.Rating;
import ru.alishev.springcourse.FirstSecurityApp.services.RatingService;
import ru.alishev.springcourse.FirstSecurityApp.services.UserService;
import ru.alishev.springcourse.FirstSecurityApp.util.Response;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/rating")
@CrossOrigin(origins = "http://localhost:4200")
@Data
public class RatingController {

    final RatingService service;
    final UserService userService;


    @GetMapping("/all-rating")
    public List<Rating> getAll(){
        return service.getAllRating();
    }

    @GetMapping("/sort-rating")
    public List<RatingDTO> getSortedRating(){
        return service.getSortedRating();
    }

}
