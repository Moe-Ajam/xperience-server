package com.moecodes.xperienceserver.security.services;

import com.moecodes.xperienceserver.security.modules.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.security.cert.CertificateRevokedException;
import java.util.Collection;
import java.util.List;

@Slf4j
public class UserDetailsImpl implements UserDetails {
    @Serial
    private static final long serialVersionUID = 1564320111040587887L;

    private final User user;

    public UserDetailsImpl(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(user.getRole().getName()));
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }
}
