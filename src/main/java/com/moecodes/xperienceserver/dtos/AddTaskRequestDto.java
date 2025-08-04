package com.moecodes.xperienceserver.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddTaskRequestDto {
    private String title;
    private String description;
}
