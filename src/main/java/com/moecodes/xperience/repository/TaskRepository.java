package com.moecodes.xperience.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.moecodes.xperience.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {

}
