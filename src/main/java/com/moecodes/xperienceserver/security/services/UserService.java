package com.moecodes.xperienceserver.security.services;

import com.moecodes.xperienceserver.security.modules.User;
import com.moecodes.xperienceserver.security.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User findByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        return user.orElseThrow(() -> new UsernameNotFoundException("User with the username: " + username + " is not found"));
    }
}
