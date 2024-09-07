package com.example.recrutement.initializer;

import com.example.recrutement.entity.User;
import com.example.recrutement.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepo userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (userRepository.findAll().isEmpty()) {
            User defaultUser = new User();
            defaultUser.setUserName("oussama");
            defaultUser.setEmail("oussamalabiadh100@gmail.com");
            defaultUser.setPassword(passwordEncoder.encode("123456"));
            defaultUser.setRole("ADMIN");
            userRepository.save(defaultUser);
            System.out.println("Default user created.");
        } else {
            System.out.println("Default user already exists.");
        }
    }
}
