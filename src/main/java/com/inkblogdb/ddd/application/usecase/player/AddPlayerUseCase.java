package com.inkblogdb.ddd.application.usecase.player;

import com.inkblogdb.ddd.application.usecase.abort.ExistsPlayerException;
import com.inkblogdb.ddd.domain.model.player.PlayerId;
import com.inkblogdb.ddd.domain.model.player.PlayerName;
import com.inkblogdb.ddd.domain.model.player.PlayerRepository;
import com.inkblogdb.ddd.domain.model.player.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AddPlayerUseCase {

  private final PlayerRepository playerRepository;
  private final PlayerService playerService;

  public void addPlayer(PlayerId playerId, PlayerName playerName) throws ExistsPlayerException {
    if (playerRepository.exists(playerId)) {
      throw new ExistsPlayerException("プレイヤーが重複");
    }
    playerService.addPlayer(playerId, playerName);
  }

}
