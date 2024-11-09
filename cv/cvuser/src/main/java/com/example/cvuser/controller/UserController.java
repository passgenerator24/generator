package com.example.cvuser.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
 
import com.example.cvuser.model.CredentialModel;
import com.example.cvuser.model.UserModel;
import com.example.cvuser.service.CredentialService;
import com.example.cvuser.service.UserService;

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
        model.addAttribute("registerRequest", new UserModel());
        return "register_page";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model) {
        model.addAttribute("loginRequest", new UserModel());
        return "login_page";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute UserModel userModel) {
        System.out.println("register request:" + userModel);
        UserModel registeredUser = userService.registerUser(userModel.getLogin(), userModel.getPassword(),
                userModel.getEmail());
        return registeredUser == null ? "error_page" : "redirect:/login";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute UserModel userModel, Model model) {
        System.out.println("login request:" + userModel);
        UserModel authenticated = userService.authenticate(userModel.getLogin(), userModel.getPassword());
        if (authenticated != null) {
            model.addAttribute("userLogin", authenticated.getLogin());
            List<CredentialModel> credentials = credentialService.getPasswordsByUserId(authenticated.getId());
            model.addAttribute("credentials", credentials); 
            return "personal_page"; 
        } else {
            model.addAttribute("errorMessage", "Account not found.");
            return "redirect:/login";
        }
    }

}




