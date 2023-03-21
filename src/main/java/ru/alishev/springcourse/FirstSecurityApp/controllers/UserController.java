package ru.alishev.springcourse.FirstSecurityApp.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.alishev.springcourse.FirstSecurityApp.dto.UserDTO;
import ru.alishev.springcourse.FirstSecurityApp.models.User;
import ru.alishev.springcourse.FirstSecurityApp.services.UserService;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/users")
@CrossOrigin(origins = "http://localhost:4200")

public class UserController {

    final UserService service;

    @GetMapping()
    public List<User> getAll(){
        return service.getAllUsers();
    }

    @PostMapping("/user")
    public User getUserByEmail(@RequestBody UserDTO userDTO){
        return service.getUserByEmail(userDTO.getEmail());
    }


}
