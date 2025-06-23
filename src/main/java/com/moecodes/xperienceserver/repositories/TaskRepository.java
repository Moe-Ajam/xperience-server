package com.moecodes.xperienceserver.repositories;

import com.moecodes.xperienceserver.domain.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface TaskRepository extends CrudRepository<Task,Long> {
}
