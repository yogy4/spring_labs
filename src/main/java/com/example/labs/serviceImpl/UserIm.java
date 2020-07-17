package com.example.labs.serviceImpl;

import com.example.labs.models.User;
import com.example.labs.repositories.RoleRepository;
import com.example.labs.repositories.UserRepository;
import com.example.labs.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.HashSet;

@Service
public class UserIm implements UserService {
    @Autowired 
    private UserRepository userRepository;
    @Autowired 
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(new HashSet(roleRepository.findAll()));
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }

}