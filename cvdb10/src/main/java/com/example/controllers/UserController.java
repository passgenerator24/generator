package com.example.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.models.Credential;
import com.example.models.User;
import com.example.services.CredentialService;
import com.example.services.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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

    @GetMapping("/login")
    public String getLoginPage(Model model) {
        model.addAttribute("loginRequest", new User());
        return "credentials/login_page";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User userModel, Model model) 
    {
        System.out.println("register request: " + userModel);
        try {
            User registeredUser = userService.registerUser(userModel.getLogin(), userModel.getPassword(),
                    userModel.getEmail());
            return "redirect:/login";
        } catch (IllegalArgumentException e) {
            model.addAttribute("registerRequest", userModel);
            model.addAttribute("errorMessage", e.getMessage());
            return "credentials/login_page";
        }
    }

    @GetMapping("/personal_page/{id}")
    public String getPersonalPage(@PathVariable Integer id, Model model, HttpServletRequest request) {
    User user = (User) request.getSession().getAttribute("user");
    
    //session not being saved in edit form, hidden until a better fix is implemented
    /*if (user == null || !user.getId().equals(id)) {
        return "redirect:/login";
    }*/

    List<Credential> credentials = credentialService.findByUserId(user.getId());
    model.addAttribute("credentials", credentials); 
    model.addAttribute("userLogin", user.getLogin());
    model.addAttribute("user", user);

    return "credentials/personal_page"; 
}

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute User userModel, Model model, HttpServletRequest request) {
        System.out.println("login request:" + userModel);
        User authenticated = userService.authenticate(userModel.getLogin(), userModel.getPassword());
        if (authenticated != null) {
            request.getSession().setAttribute("user", authenticated);
            model.addAttribute("user", authenticated);
            return "redirect:/personal_page/" + authenticated.getId();
        } else {
            model.addAttribute("errorMessage", "Invalid username/password.");
            model.addAttribute("loginRequest", userModel);
            return "credentials/login_page";
        }
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().invalidate();

        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);

        return "redirect:/login";
    }

}