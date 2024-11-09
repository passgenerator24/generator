package com.example.cvuser.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cvuser.model.CredentialModel;

public interface CredentialRepo extends JpaRepository<CredentialModel, Integer> {
    List<CredentialModel> findByUserId(Integer userId);

}
