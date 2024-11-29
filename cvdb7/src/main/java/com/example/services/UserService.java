package com.example.services;

import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
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
        if (login == null || password == null || email == null) {
            throw new IllegalArgumentException("Please fill all fields.");
        }

        if (userRepository.existsByLogin(login) && userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("There already exists an account with this username and email.");
        }

        if (userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("This email address is already in use.");
        }

        if (userRepository.existsByLogin(login)) {
            throw new IllegalArgumentException("This username is already taken.");
        }

        try {
            User user = new User();
            user.setLogin(login);
            user.setPassword(password);
            user.setEmail(email);

            return userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("The email address or username is already taken.");
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

