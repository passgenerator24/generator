package com.example.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.models.Credential;
import com.example.models.CredentialDto;
import com.example.repository.CredentialRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/credentials")
public class CredentialController {

    // private final UserService userService;
    // private final CredentialService credentialService;

    @Autowired
    private CredentialRepository repo;
    // public CredentialController(CredentialService credentialService, UserService userService) {
    //     this.credentialService = credentialService;
    //     this.userService = userService;
    // }
    

    @GetMapping({ "", "/" })
    public String showCredentialList(Model model) {
        // User user = userService.findById(userId);
        // List<Credential> credentials = credentialService.getPasswordsByUserId(userId);
        List<Credential> credentials = repo.findAll();
        model.addAttribute("credentials", credentials);
        return "credentials/index";
    }

    @GetMapping("/create")
    public String showCreatePage(Model model) {
        CredentialDto credentialDto = new CredentialDto();
        model.addAttribute("credentialDto", credentialDto);
        return "credentials/CreateCredentials";
    }

    @PostMapping("/create")
    public String createCredential(@Valid @ModelAttribute CredentialDto credentialDto, BindingResult result) {
        Credential credential = new Credential();
        credential.setAccount(credentialDto.getAccount());
        credential.setUsername(credentialDto.getUsername());
        credential.setPassword(credentialDto.getPassword());
        Date createdAt = new Date();
        credential.setCreatedAt(createdAt);
        repo.save(credential);
        return "redirect:/credentials";
    }



    @GetMapping("/edit")
    public String showEditPage(Model model, @RequestParam int id) {
        try {
            Credential credential = repo.findById(id).get();
            model.addAttribute("credential", credential);

            CredentialDto credentialDto = new CredentialDto();
            credentialDto.setAccount(credential.getAccount());
            credentialDto.setUsername(credential.getUsername());
            credentialDto.setPassword(credential.getPassword());
            // Date createdAt = new Date();
            // productDto.setCreatedAt(createdAt);
            model.addAttribute("credentialDto", credentialDto);

        } catch (Exception e) {
            return "redirect:/credentials";
        }
        return "credentials/EditCredential";
    }

    @PostMapping("/edit")
    public String updateCredential(Model model, @RequestParam int id,
            @Valid @ModelAttribute CredentialDto credentialDto,
            BindingResult result) {
        try {
            Credential credential = repo.findById(id).get();
            model.addAttribute("credential", credential);
            if (result.hasErrors()) {
                return "credentials/EditCredential";
            }
            credential.setAccount(credentialDto.getAccount());
            credential.setUsername(credentialDto.getUsername());
            credential.setPassword(credentialDto.getPassword());

            repo.save(credential);

        } catch (Exception e) {
        }
        return "redirect:/credentials";
    }

    @GetMapping("/delete")
    public String deleteCredential(@RequestParam int id) {
        try {
            Credential credential = repo.findById(id).get();
            repo.delete(credential);
        } catch (Exception e) {
        }
        return "redirect:/credentials";
    }
}