package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Register a new user
    public User register(User user) throws Exception {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new Exception("Email already in use");
        }
        // No password encoding
        return userRepository.save(user);
    }

    // Login (checks raw password)
    public User login(String email, String password) throws Exception {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new Exception("User not found"));

        if (!user.getPassword().equals(password)) {
            throw new Exception("Invalid password");
        }
        return user;
    }
}
