package com.moecodes.xperienceserver.repositories;

import com.moecodes.xperienceserver.domain.Player;
import org.springframework.data.repository.CrudRepository;

public interface PlayerRepository extends CrudRepository<Player, Long> {
}
