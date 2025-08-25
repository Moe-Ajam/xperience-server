package com.moecodes.xperienceserver;

import com.moecodes.xperienceserver.dtos.AddTaskRequestDto;
import com.moecodes.xperienceserver.dtos.TaskDto;
import com.moecodes.xperienceserver.dtos.UpdateTaskDto;
import com.moecodes.xperienceserver.services.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @GetMapping("/tasks")
    public ResponseEntity<?> getTaskForUser(@AuthenticationPrincipal UserDetails userDetails) {
        List<TaskDto> tasks = taskService.getTasksForUser(userDetails.getUsername());

        return ResponseEntity.ok(tasks);
    }

    @PutMapping("/tasks/toggle/{id}")
    public ResponseEntity<?> toggleTaskCompleted(@PathVariable Long id) {
        TaskDto toggledTask = taskService.toggleTaskCompleted(id);
        return ResponseEntity.ok(toggledTask);
    }

    @PostMapping("/tasks")
    public ResponseEntity<?> addTask(@AuthenticationPrincipal UserDetails userDetails, @RequestBody AddTaskRequestDto requestDto) {
        TaskDto savedTask = taskService.addTask(requestDto, userDetails.getUsername());
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTask);
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<?> deleteTask(@AuthenticationPrincipal UserDetails userDetails, @PathVariable Long id) {
        taskService.deleteTask(id, userDetails.getUsername());

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/tasks/{id}")
    public ResponseEntity<?> updateTask(@AuthenticationPrincipal UserDetails userDetails,@PathVariable Long id, @RequestBody UpdateTaskDto requestDto) {
        TaskDto savedTask = taskService.updateTask(id, requestDto, userDetails.getUsername());

        return ResponseEntity.ok(savedTask);
    }
}
