package com.moecodes.xperienceserver;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class TempController {

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello";
    }
}
