package com.moecodes.xperienceserver.repositories;

import com.moecodes.xperienceserver.modules.Task;
import com.moecodes.xperienceserver.security.modules.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findTasksByUserId(Long userId);

    Long user(User user);
}
