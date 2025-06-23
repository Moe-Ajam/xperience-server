package com.moecodes.xperienceserver.repositories;

import com.moecodes.xperienceserver.domain.Player;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface UserRepository extends CrudRepository<Player, Long> {
}
