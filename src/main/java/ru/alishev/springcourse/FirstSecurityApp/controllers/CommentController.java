package ru.alishev.springcourse.FirstSecurityApp.controllers;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.alishev.springcourse.FirstSecurityApp.dto.CommentDTO;
import ru.alishev.springcourse.FirstSecurityApp.models.Comment;
import ru.alishev.springcourse.FirstSecurityApp.models.User;
import ru.alishev.springcourse.FirstSecurityApp.services.CommentService;
import ru.alishev.springcourse.FirstSecurityApp.services.UserService;
import ru.alishev.springcourse.FirstSecurityApp.util.Response;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/comment")
public class CommentController {

    final CommentService commentService;
    final UserService userService;
    final ModelMapper modelMapper;


    @PostMapping("/create")
    public ResponseEntity<?> addComment(@RequestBody @Valid CommentDTO commentDTO, BindingResult bindingResult){


        if(bindingResult.hasErrors()){
            StringBuilder errors = new StringBuilder();
            List<FieldError> errorList = bindingResult.getFieldErrors();
            for(FieldError error: errorList)
                errors.append(error.getField()).append(" - ").append(error.getDefaultMessage()).append("; ");

            return ResponseEntity.badRequest().body(new Response(HttpStatus.BAD_GATEWAY,errors.toString()));
        }

        if(userService.getUserById(commentDTO.getId()) == null){
            return ResponseEntity.badRequest().body(new Response(HttpStatus.NOT_FOUND,"user doesnt exist"));
        }

        Comment comment = new Comment();
        comment.setComment(commentDTO.getText());
        User user = userService.getUserById(commentDTO.getId());
        comment.setUser(user);


        commentService.addComment(comment);
        return ResponseEntity.ok("good");
    }

}
