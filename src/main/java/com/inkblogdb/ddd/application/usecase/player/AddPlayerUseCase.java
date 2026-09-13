package com.inkblogdb.ddd.application.usecase.player;

import com.inkblogdb.ddd.application.dto.player.PlayerDTO;
import com.inkblogdb.ddd.domain.model.player.PlayerId;
import com.inkblogdb.ddd.domain.model.player.PlayerName;
import com.inkblogdb.ddd.domain.model.player.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AddPlayerUseCase {

  private final PlayerService playerService;

  public PlayerDTO addPlayer(PlayerName playerName) {
    PlayerId playerId = playerService.addPlayer(playerName);
    return new PlayerDTO(
        playerId.value(),
        playerName.value()
    );
  }

}
