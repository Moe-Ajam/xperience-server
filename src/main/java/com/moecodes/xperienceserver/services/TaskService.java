package com.moecodes.xperienceserver.services;

import com.moecodes.xperienceserver.dtos.TasksDto;
import com.moecodes.xperienceserver.modules.Task;
import com.moecodes.xperienceserver.repositories.TaskRepository;
import com.moecodes.xperienceserver.security.modules.User;
import com.moecodes.xperienceserver.security.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public List<TasksDto> getTasksForUser(String username) {
        User user = userRepository.findByUsername(username).orElseThrow();
        List<Task> tasks = taskRepository.findTasksByUserId(user.getId());
        return tasks.stream().map(this::convertToUserTasksDto).toList();
    }

    private TasksDto convertToUserTasksDto(Task task) {
        TasksDto tasksDto = new TasksDto();
        tasksDto.setId(task.getId());
        tasksDto.setTitle(task.getTitle());
        tasksDto.setDescription(task.getDescription());
        tasksDto.setCompleted(task.getCompleted());
        tasksDto.setCreatedAt(task.getCreatedAt());
        tasksDto.setUpdatedAt(task.getUpdatedAt());
        return tasksDto;
    }

    public TasksDto toggleTaskCompleted(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Task with id " + id + " not found"));
        task.setCompleted(!task.getCompleted());
        taskRepository.save(task);
        return convertToUserTasksDto(task);
    }


}
