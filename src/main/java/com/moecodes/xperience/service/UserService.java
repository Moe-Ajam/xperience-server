package com.moecodes.xperience.service;

import org.springframework.stereotype.Service;

import com.moecodes.xperience.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
