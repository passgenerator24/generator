package com.example.cvuser.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.cvuser.model.UserModel;
import com.example.cvuser.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserModel registerUser(String login, String password, String email) {
        if (login == null || password == null) {
            return null; 
        } else {
            UserModel userModel = new UserModel();
            userModel.setLogin(login);
            userModel.setPassword(password); 
            userModel.setEmail(email);
            return userRepository.save(userModel);
        }
    }

    public UserModel authenticate(String login, String password) {
        return userRepository.findByLoginAndPassword(login, password).orElse(null);
    }

    public UserModel findById(Integer userId) {
        Optional<UserModel> user = userRepository.findById(userId);
        return user.orElse(null); 
    }
}

