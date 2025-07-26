package com.moecodes.xperienceserver.dtos;

import lombok.Getter;
import lombok.Setter;

/*
Generic message response payload
 */
@Getter
@Setter
public class MessageResponse {
    private String message;

    public MessageResponse(String message) {
        this.message = message;
    }
}
