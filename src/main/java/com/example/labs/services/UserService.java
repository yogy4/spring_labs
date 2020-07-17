package com.example.labs.services;
import com.example.labs.models.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}