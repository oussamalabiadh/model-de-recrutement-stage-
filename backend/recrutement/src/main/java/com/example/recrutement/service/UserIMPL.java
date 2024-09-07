package com.example.recrutement.service;

import com.example.recrutement.dto.loginDTO;
import com.example.recrutement.entity.User;
import com.example.recrutement.response.loginResponse;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.recrutement.repository.UserRepo;

import java.sql.Date;
import java.util.Optional;


@Service
public class UserIMPL implements UserService, UserToken {

    private final long expirationTime = 86400000; // 1 day in milliseconds
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepo userRepo;
    @Override
    public Claims buildClaims(String userId, String userName, String email, String role) {
        Claims claims = Jwts.claims().setSubject(userId);
        claims.put("userName", userName);
        claims.put("email", email);
        claims.put("role", role);
        return claims;
    }

    @Override
    public String generateToken(String userId, String userName, String email, String role) {
        Claims claims = this.buildClaims(userId, userName, email, role );

        long now = System.currentTimeMillis();
        Date expiration = new Date(now + this.expirationTime);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(now))
                .setExpiration(expiration)
                .signWith(Keys.secretKeyFor(SignatureAlgorithm.HS256), SignatureAlgorithm.HS256)
                .compact();
    }


    @Override
    public loginResponse loginUser(loginDTO loginDTO) {
        User user = userRepo.findByEmail(loginDTO.getUserEmail());

        if (user != null) {
            String password = loginDTO.getUserPassword();
            String encodedPassword = user.getPassword();

            if (passwordEncoder.matches(password, encodedPassword)) {
                Optional<User> foundUser = userRepo.findOneByEmailAndPassword(loginDTO.getUserEmail(), encodedPassword);
                if (foundUser.isPresent()) {
                    String token = this.generateToken(String.valueOf(user.getUserID()), user.getUserName(), user.getEmail(), user.getRole());
                    return new loginResponse("Login Success", true, token);
                } else {
                    return new loginResponse("Login Failed", false, "USER NOT FOUND");
                }
            } else {
                return new loginResponse("Password Not Match", false, "USER NOT FOUND");
            }
        } else {
            return new loginResponse("Email not exists", false, "USER NOT FOUND");
        }
    }


}
