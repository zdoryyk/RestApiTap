package ru.alishev.springcourse.FirstSecurityApp.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.alishev.springcourse.FirstSecurityApp.repositories.RatingRepository;

@AllArgsConstructor
@Service
public class RatingService {

    final RatingRepository repository;
}
