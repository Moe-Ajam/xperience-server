package com.moecodes.xperienceserver.dtos;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class LoginResponse {

    private String username;
    private List<String> roles;
    private String jwtToken;

}