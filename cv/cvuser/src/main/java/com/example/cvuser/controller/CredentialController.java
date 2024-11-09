package com.example.cvuser.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.cvuser.model.CredentialModel;
import com.example.cvuser.model.UserModel;
import com.example.cvuser.service.CredentialService;
import com.example.cvuser.service.UserService;

@Controller
public class CredentialController {

    private final CredentialService credentialService;
    private final UserService userService;

    @Autowired
    public CredentialController(CredentialService credentialService, UserService userService) {
        this.credentialService = credentialService;
        this.userService = userService;
    }

    @GetMapping("/personal_page")
    public String showUserCredentials(@RequestParam("userId") Integer userId, Model model) {
        UserModel user = userService.findById(userId);
        List<CredentialModel> credentials = credentialService.getPasswordsByUserId(userId);
        model.addAttribute("userLogin", user.getLogin());
        model.addAttribute("credentials", credentials);
        return "personal_page";
    }

    @PostMapping("/users/{userId}/credentials")
    public CredentialModel addCredential(@PathVariable Integer userId, @RequestBody CredentialModel credentialModel) {
        return credentialService.savePasswordForUser(userId, credentialModel);
    }

}










