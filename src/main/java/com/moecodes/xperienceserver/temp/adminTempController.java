package com.moecodes.xperienceserver.temp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class adminTempController {

    @GetMapping("/hello")
    public String helloAdmin() {
        return "Big Hello to the ADMIN!";
    }
}
