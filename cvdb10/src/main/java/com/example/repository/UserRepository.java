package com.example.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.models.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsByLogin(String login);
    boolean existsByEmail(String email);
    Optional<User> findByLoginAndPassword(String login, String password);
}
