package com.inkblogdb.ddd.domain.model.player;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlayerService {

  private final PlayerRepository playerRepository;

  public void addPlayer(PlayerId playerId, PlayerName playerName) {
    playerRepository.addPlayer(playerId, playerName);
  }

}
