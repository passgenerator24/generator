package com.example.rest.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.rest.Models.Credentials;
import com.example.rest.Repo.CredentialRepo;

@RestController
public class ApiControllers {
    @Autowired
    private CredentialRepo credentialRepo;

    @GetMapping(value="/")
    public String getPage() {
        return "Welcome";
    }

    @GetMapping(value="/credentials")
    public List<Credentials> getCredential() {
        return credentialRepo.findAll();
    }

    @PostMapping(value="/save")
    public String saveCredentials(@RequestBody Credentials credential) {
        credentialRepo.save(credential);
        return "Saved...";
    }

    @DeleteMapping(value = "/delete/{id}")
    public String deleteCredentials(@PathVariable long id) {
        credentialRepo.deleteById(id);
        return "Deleted...";
    }
}
