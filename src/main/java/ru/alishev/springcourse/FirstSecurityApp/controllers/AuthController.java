package ru.alishev.springcourse.FirstSecurityApp.controllers;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.alishev.springcourse.FirstSecurityApp.Auth.config.AuthenticationDTO;
import ru.alishev.springcourse.FirstSecurityApp.Auth.config.Token;
import ru.alishev.springcourse.FirstSecurityApp.controllers.UserController;
import ru.alishev.springcourse.FirstSecurityApp.dto.UserDTO;
import ru.alishev.springcourse.FirstSecurityApp.models.User;
import ru.alishev.springcourse.FirstSecurityApp.Auth.config.security.JWTUtil;
import ru.alishev.springcourse.FirstSecurityApp.Auth.config.services.RegistrationService;
import ru.alishev.springcourse.FirstSecurityApp.services.UserService;
import ru.alishev.springcourse.FirstSecurityApp.util.PersonValidator;
import ru.alishev.springcourse.FirstSecurityApp.util.Response;

import javax.validation.Valid;

/**
 * @author Neil Alishev
 */
@RestController
@RequestMapping("/api/auth/")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    private final RegistrationService registrationService;
    private final JWTUtil jwtUtil;
    private final ModelMapper modelMapper;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    @Autowired
    public AuthController(RegistrationService registrationService, PersonValidator personValidator,
                          JWTUtil jwtUtil, ModelMapper modelMapper, AuthenticationManager authenticationManager, UserController userController, UserService userService) {
        this.registrationService = registrationService;
        this.jwtUtil = jwtUtil;
        this.modelMapper = modelMapper;
        this .authenticationManager = authenticationManager;
        this.userService = userService;
    }

    @PostMapping("/register")
    @CrossOrigin(origins = "http://localhost:4200")
    public Object performRegistration(@RequestBody @Valid UserDTO userDTO, BindingResult bindingResult) {
        System.out.println(userDTO);
        User user = convertToPerson(userDTO);
        System.out.println(user);

        if(userService.getUserByEmail(userDTO.getEmail()) != null){
            return ResponseEntity.badRequest().body(new Response(HttpStatus.LOCKED,"THIS_EMAIL_TAKEN"));
        }
        if(userService.getByUserName(userDTO.getUsername()) != null){
            return ResponseEntity.badRequest().body(new Response(HttpStatus.LOCKED,"THIS_USERNAME_TAKEN"));
        }
        registrationService.register(user);


        String token = jwtUtil.generateToken(user.getEmail());

        user = userService.getUserByEmail(userDTO.getEmail());


        return new Token(user.getId(),user.getEmail(),user.getUsername(),token);
    }

    @PostMapping("/login")
    @CrossOrigin(origins = "http://localhost:4200")
    public Object performLogin(@RequestBody AuthenticationDTO authenticationDTO) {

        if(userService.getUserByEmail(authenticationDTO.getEmail()) == null) {
            return ResponseEntity.badRequest().body(new Response(HttpStatus.FORBIDDEN,"USER_NOT_FOUND"));
        }

        if(authenticationDTO.getPassword() != null && !authenticationDTO.getPassword().isEmpty() && !authenticationDTO.getPassword().isBlank()) {

            UsernamePasswordAuthenticationToken authInputToken =
                    new UsernamePasswordAuthenticationToken(authenticationDTO.getEmail(),
                            authenticationDTO.getPassword());

            try {
                authenticationManager.authenticate(authInputToken);
            } catch (BadCredentialsException e) {
                return ResponseEntity.badRequest().body(new Response(HttpStatus.LOCKED, "INCORRECT_PASSWORD_EMAIL"));
            }
        }

        String token = jwtUtil.generateToken(authenticationDTO.getEmail());
        User user = userService.getUserByEmail(authenticationDTO.getEmail());

        return new Token(user.getId(),user.getEmail(),user.getUsername(),token);
    }

    public User convertToPerson(UserDTO userDTO) {
        return this.modelMapper.map(userDTO, User.class);
    }
}
