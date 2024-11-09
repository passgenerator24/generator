package com.example.cvuser.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cvuser.model.CredentialModel;
import com.example.cvuser.model.UserModel;
import com.example.cvuser.repository.CredentialRepo;
import com.example.cvuser.repository.UserRepository;

@Service
public class CredentialService {

    @Autowired
    private CredentialRepo credentialRepo;

    @Autowired
    private UserRepository userRepository;

    public List<CredentialModel> getPasswordsByUserId(Integer userId) {
        return credentialRepo.findByUserId(userId);
    }

    public CredentialModel savePasswordForUser(Integer userId, CredentialModel credentialModel) {
        UserModel user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        credentialModel.setUser(user);
        return credentialRepo.save(credentialModel);
    }
}
