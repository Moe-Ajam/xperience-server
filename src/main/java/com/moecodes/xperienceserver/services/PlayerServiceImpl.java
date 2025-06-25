package com.moecodes.xperienceserver.services;

import com.moecodes.xperienceserver.domain.Player;
import com.moecodes.xperienceserver.repositories.PlayerRepository;
import org.springframework.stereotype.Service;

@Service
public class PlayerServiceImpl implements PlayerService {

    final private PlayerRepository playerRepository;

    public PlayerServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public Iterable<Player> findAll() {
        return playerRepository.findAll();
    }
}