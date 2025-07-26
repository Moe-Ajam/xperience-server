package com.moecodes.xperienceserver.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserInfoResponse {
    private Long id;
    private String username;
    private String email;
    private boolean enabled;
    private List<String> roles;

}
