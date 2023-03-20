package ru.alishev.springcourse.FirstSecurityApp.Auth.config;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.alishev.springcourse.FirstSecurityApp.controllers.UserController;
import ru.alishev.springcourse.FirstSecurityApp.dto.UserDTO;
import ru.alishev.springcourse.FirstSecurityApp.models.User;
import ru.alishev.springcourse.FirstSecurityApp.Auth.config.security.JWTUtil;
import ru.alishev.springcourse.FirstSecurityApp.Auth.config.services.RegistrationService;
import ru.alishev.springcourse.FirstSecurityApp.services.UserService;
import ru.alishev.springcourse.FirstSecurityApp.util.PersonValidator;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author Neil Alishev
 */
@RestController
@RequestMapping("/api/auth/")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    private final RegistrationService registrationService;
    private final PersonValidator personValidator;
    private final JWTUtil jwtUtil;
    private final ModelMapper modelMapper;
    private final AuthenticationManager authenticationManager;

    private final UserService userService;

    @Autowired
    public AuthController(RegistrationService registrationService, PersonValidator personValidator,
                          JWTUtil jwtUtil, ModelMapper modelMapper, AuthenticationManager authenticationManager, UserController userController, UserService userService) {
        this.registrationService = registrationService;
        this.personValidator = personValidator;
        this.jwtUtil = jwtUtil;
        this.modelMapper = modelMapper;
        this .authenticationManager = authenticationManager;
        this.userService = userService;
    }

    @PostMapping("/register")
    @CrossOrigin(origins = "http://localhost:4200")
    public Token performRegistration(@RequestBody @Valid UserDTO userDTO,
                                                                         BindingResult bindingResult) {
        User user = convertToPerson(userDTO);

        registrationService.register(user);


        String token = jwtUtil.generateToken(user.getEmail());

        user = userService.getUserByEmail(userDTO.getEmail());

        return new Token(user.getId(),user.getEmail(),user.getUsername(),token);
    }

    @PostMapping("/login")
    @CrossOrigin(origins = "http://localhost:4200")
    public Map<String, String> performLogin(@RequestBody AuthenticationDTO authenticationDTO) {

        UsernamePasswordAuthenticationToken authInputToken =
                new UsernamePasswordAuthenticationToken(authenticationDTO.getEmail(),
                        authenticationDTO.getPassword());

        try {
            authenticationManager.authenticate(authInputToken);
        } catch (BadCredentialsException e) {
            return Map.of("message", "Incorrect credentials!");
        }

        String token = jwtUtil.generateToken(authenticationDTO.getEmail());
        return Map.of("jwt-token", token);
    }

    public User convertToPerson(UserDTO userDTO) {
        return this.modelMapper.map(userDTO, User.class);
    }
}
