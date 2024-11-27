package com.example.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.models.Credential;
import com.example.models.User;
import com.example.repository.CredentialRepository;
import com.example.repository.UserRepository;

@Service
public class CredentialService 
{

    @Autowired
    private CredentialRepository credentialRepo;

    @Autowired
    private UserRepository userRepository;

    public List<Credential> getPasswordsByUserId(Integer userId) {
        return credentialRepo.findByUserId(userId);
    }

    public List<Credential> findByUserId(Integer userId) {
        return credentialRepo.findByUserId(userId);
    }

    public void addCredential(Credential credential) {
    credentialRepo.save(credential);
    }

    public Credential savePasswordForUser(Integer userId, Credential credentialModel) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        credentialModel.setUser(user);
        return credentialRepo.save(credentialModel);
    }

    public Credential getCredentialById(Integer id) {
    return credentialRepo.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Credential not found"));
    }

}