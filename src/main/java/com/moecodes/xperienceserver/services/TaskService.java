package com.moecodes.xperienceserver.services;

import com.moecodes.xperienceserver.dtos.AddTaskRequestDto;
import com.moecodes.xperienceserver.dtos.TaskDto;
import com.moecodes.xperienceserver.dtos.UpdateTaskDto;
import com.moecodes.xperienceserver.exceptions.ResourceNotFoundException;
import com.moecodes.xperienceserver.modules.Task;
import com.moecodes.xperienceserver.repositories.TaskRepository;
import com.moecodes.xperienceserver.security.modules.User;
import com.moecodes.xperienceserver.security.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.security.access.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public List<TaskDto> getTasksForUser(String username) {
        User user = userRepository.findByUsername(username).orElseThrow();
        List<Task> tasks = taskRepository.findTasksByUserId(user.getId());
        return tasks.stream().map(this::convertToTaskDto).toList();
    }

    private TaskDto convertToTaskDto(Task task) {
        TaskDto taskDto = new TaskDto();
        taskDto.setId(task.getId());
        taskDto.setTitle(task.getTitle());
        taskDto.setDescription(task.getDescription());
        taskDto.setCompleted(task.getCompleted());
        taskDto.setCreatedAt(task.getCreatedAt());
        taskDto.setUpdatedAt(task.getUpdatedAt());
        return taskDto;
    }

    public TaskDto toggleTaskCompleted(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Task with id " + id + " not found"));
        task.setCompleted(!task.getCompleted());
        taskRepository.save(task);
        return convertToTaskDto(task);
    }

    @Transactional
    public TaskDto addTask(AddTaskRequestDto requestDto, String username) {
        Task task = new Task();
        task.setTitle(requestDto.getTitle());
        task.setDescription(requestDto.getDescription());
        User user = userRepository.findByUsername(username)
                .orElseThrow();
        task.setUser(user);
        task.setCompleted(false);
        task.setCreatedAt(LocalDateTime.now());
        task.setUpdatedAt(LocalDateTime.now());

        Task saved = taskRepository.save(task);
        return convertToTaskDto(saved);
    }

    @Transactional
    public void deleteTask(Long taskId, String username) throws AccessDeniedException {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task with id " + taskId + " not found"));

        if (!task.getUser().getUsername().equals(username)) {
            throw new AccessDeniedException("You don't own this task");
        }

        taskRepository.delete(task);

    }

    @Transactional
    public TaskDto updateTask(Long taskId, UpdateTaskDto requestDto, String username) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task with id " + taskId + "not found"));

        if (!task.getUser().getUsername().equals(username)) {
            throw new AccessDeniedException("You don't own this task");
        }

        task.setTitle(requestDto.getTitle());
        if (!requestDto.getDescription().trim().isEmpty()) {
            task.setDescription(requestDto.getDescription());
        }

        Task saved = taskRepository.save(task);
        return convertToTaskDto(saved);
    }
}
