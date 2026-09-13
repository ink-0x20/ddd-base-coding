package com.inkblogdb.ddd.domain.model.player;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlayerService {

  private final PlayerRepository playerRepository;

  public PlayerId addPlayer(PlayerName playerName) {
    Player player = new Player(
        PlayerId.generate(),
        playerName
    );
    playerRepository.save(player);
    return player.playerId();
  }

}
