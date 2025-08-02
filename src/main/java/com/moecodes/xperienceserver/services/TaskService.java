package com.moecodes.xperienceserver.services;

import com.moecodes.xperienceserver.dtos.UserTasksDto;
import com.moecodes.xperienceserver.modules.Task;
import com.moecodes.xperienceserver.repositories.TaskRepository;
import com.moecodes.xperienceserver.security.modules.User;
import com.moecodes.xperienceserver.security.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public List<UserTasksDto> getTasksForUser(String username) {
        User user = userRepository.findByUsername(username).orElseThrow();
        List<Task> tasks = taskRepository.findTasksByUserId(user.getId());
        return tasks.stream().map(this::convertToUserTasksDto).toList();
    }

    private UserTasksDto convertToUserTasksDto(Task task) {
        UserTasksDto userTasksDto = new UserTasksDto();
        userTasksDto.setTitle(task.getTitle());
        userTasksDto.setDescription(task.getDescription());
        userTasksDto.setCreatedAt(task.getCreatedAt());
        userTasksDto.setUpdatedAt(task.getUpdatedAt());
        return userTasksDto;
    }


}
