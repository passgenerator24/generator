package com.example.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.models.User;
import com.example.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(String login, String password, String email) {
        if (login == null || password == null) {
            return null; 
        } else {
            User userModel = new User();
            userModel.setLogin(login);
            userModel.setPassword(password); 
            userModel.setEmail(email);
            return userRepository.save(userModel);
        }
    }

    public User authenticate(String login, String password) {
        return userRepository.findByLoginAndPassword(login, password).orElse(null);
    }

    public User findById(Integer userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.orElse(null); 
    }
}


