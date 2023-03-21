package ru.alishev.springcourse.FirstSecurityApp.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.alishev.springcourse.FirstSecurityApp.models.User;
import ru.alishev.springcourse.FirstSecurityApp.repositories.UserRepository;

import java.util.List;

@AllArgsConstructor
@Service
public class UserService {

    final UserRepository userRepository;


    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User getUserById(Integer id){
        return userRepository.getById(id);
    }

    public User getUserByEmail(String email) {
        return userRepository.getByEmail(email);
    }

    public User getByUserName(String username) {
        return userRepository.getByUsername(username);
    }
}
