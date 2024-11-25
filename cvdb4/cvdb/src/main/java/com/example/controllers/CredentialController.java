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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.models.Credential;
import com.example.models.CredentialDto;
import com.example.models.User;
import com.example.repository.CredentialRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/credentials")
public class CredentialController {

    @Autowired
    private CredentialRepository repo;

    
    @GetMapping({ "", "/" })
    public String showCredentialList(Model model, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            List<Credential> credentials = repo.findByUserId(user.getId());
            model.addAttribute("credentials", credentials);
        } else {
            return "redirect:/login";
        }
        // return "credentials/index";
        return "credentials/personal_page";
    }
    
    @GetMapping("/create")
    public String showCreatePage(Model model, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/login"; 
        }
        
        CredentialDto credentialDto = new CredentialDto();
        model.addAttribute("credentialDto", credentialDto);
        return "credentials/CreateCredentials";
    }

    
    @PostMapping("/create")
    public String createCredential(@Valid @ModelAttribute CredentialDto credentialDto, BindingResult result, HttpServletRequest request, RedirectAttributes RedirectAttributes) 
    {
        if (result.hasErrors()) 
        {
            return "credentials/CreateCredentials";
        }

        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/login"; 
        }

        if (credentialDto.getPassword().contains(" "))
        {
            RedirectAttributes.addFlashAttribute("error message, Password cannot contain spaces.");
            return "redirect:/credentials/create";
        }

        Credential credential = new Credential();
        credential.setAccount(credentialDto.getAccount());
        credential.setUsername(credentialDto.getUsername());
        credential.setPassword(credentialDto.getPassword());
        credential.setCreatedAt(new Date());
        credential.setUser(user); 
        repo.save(credential);

        RedirectAttributes.addFlashAttribute("success message, Credentials saved successfully.");
        return "redirect:/credentials"; 
    }

    
    @GetMapping("/edit")
    public String showEditPage(Model model, @RequestParam int id, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/login"; 
        }

        try {
            Credential credential = repo.findById(id).orElseThrow(() -> new Exception("Credential not found"));
            if (!credential.getUser().getId().equals(user.getId())) {
                return "redirect:/credentials"; 
            }

            CredentialDto credentialDto = new CredentialDto();
            credentialDto.setAccount(credential.getAccount());
            credentialDto.setUsername(credential.getUsername());
            credentialDto.setPassword(credential.getPassword());
            model.addAttribute("credentialDto", credentialDto);

        } catch (Exception e) {
            return "redirect:/credentials"; 
        }

        return "credentials/EditCredential";
    }

    
    @PostMapping("/edit")
    public String updateCredential(@RequestParam int id, @Valid @ModelAttribute CredentialDto credentialDto, BindingResult result, HttpServletRequest request) {
        if (result.hasErrors()) {
            return "credentials/EditCredential";
        }

        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/login"; 
        }

        try {
            Credential credential = repo.findById(id).orElseThrow(() -> new Exception("Credential not found"));
            if (!credential.getUser().getId().equals(user.getId())) {
                return "redirect:/credentials"; 
            }

            credential.setAccount(credentialDto.getAccount());
            credential.setUsername(credentialDto.getUsername());
            credential.setPassword(credentialDto.getPassword());

            repo.save(credential);

        } catch (Exception e) {
            return "redirect:/credentials"; 
        }

        return "redirect:/credentials"; 
    }

    
    @GetMapping("/delete")
    public String deleteCredential(@RequestParam int id, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/login"; 
        }

        try {
            Credential credential = repo.findById(id).orElseThrow(() -> new Exception("Credential not found"));
            if (!credential.getUser().getId().equals(user.getId())) {
                return "redirect:/credentials"; 
            }

            repo.delete(credential);
        } catch (Exception e) {
            return "redirect:/credentials"; 
        }

        return "redirect:/credentials"; 
    }

    @PostMapping("/save")
    @ResponseBody
    public String saveCredential(@RequestBody CredentialDto credentialDto, HttpServletRequest request) 
    {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) 
        {
            return "{\"error\": \"User not logged in\"}";
        }

    
        if (credentialDto.getPassword().contains(" ")) 
        {
            return "{\"error\": \"Password cannot contain spaces\"}";
        }

    
        Credential credential = new Credential();
        credential.setAccount(credentialDto.getAccount());
        credential.setUsername(credentialDto.getUsername());
        credential.setPassword(credentialDto.getPassword());
        credential.setCreatedAt(new Date());
        credential.setUser(user); 
        return "{\"message\": \"Credential saved successfully\"}";
    }

}