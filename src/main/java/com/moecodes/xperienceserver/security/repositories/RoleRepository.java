package com.moecodes.xperienceserver.security.repositories;

import com.moecodes.xperienceserver.security.modules.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}