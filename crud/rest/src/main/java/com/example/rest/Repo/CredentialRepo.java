package com.example.rest.Repo;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.rest.Models.Credentials;

public interface CredentialRepo extends JpaRepository<Credentials, Long> {
    
}
