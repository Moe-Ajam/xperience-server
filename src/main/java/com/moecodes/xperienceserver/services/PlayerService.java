package com.moecodes.xperienceserver.services;

import com.moecodes.xperienceserver.domain.Player;

public interface PlayerService {

    Iterable<Player> findAll();
}
