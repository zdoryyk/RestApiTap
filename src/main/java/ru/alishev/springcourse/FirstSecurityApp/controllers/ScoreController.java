package ru.alishev.springcourse.FirstSecurityApp.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/scores")
@CrossOrigin(origins = "http://localhost:4200")
public class ScoreController {




}
