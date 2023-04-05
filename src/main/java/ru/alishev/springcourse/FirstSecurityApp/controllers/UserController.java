package ru.alishev.springcourse.FirstSecurityApp.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.alishev.springcourse.FirstSecurityApp.dto.UserDTO;
import ru.alishev.springcourse.FirstSecurityApp.models.User;
import ru.alishev.springcourse.FirstSecurityApp.services.UserService;
import ru.alishev.springcourse.FirstSecurityApp.util.Response;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    final UserService service;

    @GetMapping()
    public List<User> getAll(){
        return service.getAllUsers();
    }

    @PostMapping("/user/email")
    public User getUserByEmail(@RequestBody UserDTO userDTO){
        return service.getUserByEmail(userDTO.getEmail());
    }

    @PostMapping("/user/id")
    public User getUserById(@RequestBody UserDTO userDTO){
        return service.getUserById(userDTO.getId());
    }

    @DeleteMapping("/user/delete")
    public ResponseEntity<?> deleteUser(@RequestBody UserDTO userDTO){

        User user = service.getUserByEmail(userDTO.getEmail());

        if(user == null){
            return ResponseEntity.badRequest().body(new Response(HttpStatus.NOT_FOUND,"NOT_FOUND"));
        }
        if(user.getRole().equals("ROLE_ADMIN")){
            return ResponseEntity.badRequest().body(new Response(HttpStatus.LOCKED,"YOU CANT DELETE ADMIN"));
        }
        service.deleteUser(user);
        System.out.println("123");
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
