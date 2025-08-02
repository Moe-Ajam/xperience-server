package com.moecodes.xperienceserver;

import com.moecodes.xperienceserver.dtos.UserTasksDto;
import com.moecodes.xperienceserver.services.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @GetMapping("/tasks")
    public ResponseEntity<?> getTaskForUser(@AuthenticationPrincipal UserDetails userDetails) {
        List<UserTasksDto> tasks = taskService.getTasksForUser(userDetails.getUsername());

        return ResponseEntity.ok(tasks);
    }
}
