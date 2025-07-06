package com.moecodes.xperienceserver.repository;

import com.moecodes.xperienceserver.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
