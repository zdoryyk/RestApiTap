package ru.alishev.springcourse.FirstSecurityApp.Auth.config.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.alishev.springcourse.FirstSecurityApp.controllers.UserController;
import ru.alishev.springcourse.FirstSecurityApp.dto.UserDTO;
import ru.alishev.springcourse.FirstSecurityApp.models.User;
import ru.alishev.springcourse.FirstSecurityApp.services.UserService;

import java.time.ZonedDateTime;
import java.util.Date;

/**
 * @author Neil Alishev
 */
@Component
public class JWTUtil {

    @Value("${jwt_secret}")
    private String secret;
    private UserService userService;

    @Autowired
    public JWTUtil(UserService userService) {
        this.userService = userService;
    }

    public String generateToken(String email) {
         Date expirationDate = Date.from(ZonedDateTime.now().plusMinutes(60).toInstant());

        User user = userService.getUserByEmail(email);

        return JWT.create()
                .withSubject("User details")
                .withClaim("email",email)
                .withClaim("id",user.getId())
                .withClaim("role",user.getRole())
                .withIssuedAt(new Date())
                .withIssuer("Spring")
                .withExpiresAt(expirationDate)
                .sign(Algorithm.HMAC256(secret));
    }

    public String validateTokenAndRetrieveClaim(String token) throws JWTVerificationException {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret))
                .withSubject("User details")
                .withIssuer("Spring")
                .build();

        DecodedJWT jwt = verifier.verify(token);
        return jwt.getClaim("email").asString();
    }
}
