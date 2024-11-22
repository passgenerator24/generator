package com.example.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import com.example.models.User;

public interface UserRepository extends JpaRepositoryImplementation<User, Integer>{
    Optional<User> findByLoginAndPassword(String login, String password);
    // Optional<User> findByUserId(Integer id);
    
}

