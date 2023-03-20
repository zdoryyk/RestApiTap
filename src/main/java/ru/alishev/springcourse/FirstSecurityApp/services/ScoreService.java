package ru.alishev.springcourse.FirstSecurityApp.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.alishev.springcourse.FirstSecurityApp.repositories.ScoreRepository;

@AllArgsConstructor
@Service
public class ScoreService {

    final ScoreRepository scoreRepository;
}
