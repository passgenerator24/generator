package com.example.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.models.Credential;
import com.example.models.User;
import com.example.services.CredentialService;
import com.example.services.UserService;

@Controller
public class UserController {

    private final UserService userService;
    private final CredentialService credentialService;

    @Autowired
    public UserController(UserService userService, CredentialService credentialService) {
        this.userService = userService;
        this.credentialService = credentialService;
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        model.addAttribute("registerRequest", new User());
        return "register_page";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User userModel) {
        System.out.println("register request:" + userModel);
        User registeredUser = userService.registerUser(userModel.getLogin(), userModel.getPassword(),
                userModel.getEmail());
        return registeredUser == null ? "error_page" : "redirect:/login";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model) {
        model.addAttribute("loginRequest", new User());
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute User userModel, Model model) {
        System.out.println("login request:" + userModel);
        User authenticated = userService.authenticate(userModel.getLogin(), userModel.getPassword());
        if (authenticated != null) {
            model.addAttribute("userLogin", authenticated.getLogin());
            List<Credential> credentials = credentialService.getPasswordsByUserId(authenticated.getId());
            model.addAttribute("credentials", credentials); 
            return "credentials/index"; 
        } else {
            model.addAttribute("errorMessage", "Account not found.");
            return "redirect:/login";
        }
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/login";
    }

}



