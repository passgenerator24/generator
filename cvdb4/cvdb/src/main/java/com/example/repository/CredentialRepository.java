package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.models.Credential;

public interface CredentialRepository extends JpaRepository<Credential, Integer> {
    List<Credential> findByUserId(Integer userId);

}