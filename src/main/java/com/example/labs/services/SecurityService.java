package com.example.labs.services;

public interface SecurityService {
    String findLoggedInUsername();

    void autLogin(String username, String password);
}