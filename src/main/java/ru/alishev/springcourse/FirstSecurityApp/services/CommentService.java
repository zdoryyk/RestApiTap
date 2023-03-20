package ru.alishev.springcourse.FirstSecurityApp.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.alishev.springcourse.FirstSecurityApp.models.Comment;
import ru.alishev.springcourse.FirstSecurityApp.repositories.CommentRepository;

import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class CommentService {

    final CommentRepository commentRepository;

    public void addComment(Comment comment){
        comment.setCommentedOn(LocalDateTime.now());
        commentRepository.save(comment);
    }
}
