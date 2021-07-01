package com.example.labs.controllers;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.example.labs.models.User;
import com.example.labs.services.SecurityService;
import com.example.labs.services.UserService;
import com.example.labs.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Controller
public  class UserController {
    @Autowired
    private UserService userService;

    @Autowired 
    private SecurityService securityService;

    @Autowired 
    private UserValidator userValidator;

    @GetMapping("/registration")
    public String registration(Model model){
        model.addAttribute("userForm", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult){
        userValidator.validate(userForm, bindingResult);
        if(bindingResult.hasErrors()){
            return "registration";
        }
        userService.save(userForm);
        securityService.autLogin(userForm.getUsername(), userForm.getPassword());
        return "redirect:/welcome";
    }

    @GetMapping("/login")
    public String login(Model model, String error, String logout){
        if(error != null){
            model.addAttribute("error", "Your Username and Password Invalid");
        }
        if(logout != null){
            model.addAttribute("message", "You have been logged out successfully");
            
        }
        return "login";
    }

    @GetMapping({"/", "/welcome"})
    public String welcome(Model model){
        return "welcome";
    }

    @PostMapping("/login_api")
    public User loginApi(@RequestParam("username") String username, @RequestParam("password") String password){
        String token = getJWTToken(username);
        User user = new User();
        user.setUsername(username);
        user.setToken(token);
        return user;
    }

    private String getJWTToken(String username){
        String secretKey = "o0234";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");
        String token = Jwts
                    .builder()
                    .setId("yogJWT")
                    .setSubject(username)
                    .claim("authorities", grantedAuthorities.stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList()))
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis() + 60000))
                    .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();
        return "Bearer " + token;

    }

   
}