package com.inkblogdb.ddd.application.usecase.player;

import com.inkblogdb.ddd.application.command.player.AddPlayerCommand;
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

  public void addPlayer(AddPlayerCommand addPlayerCommand) throws ExistsPlayerException {
    PlayerId playerId = new PlayerId(addPlayerCommand.id());
    PlayerName playerName = new PlayerName(addPlayerCommand.name());
    if (playerRepository.exists(playerId)) {
      throw new ExistsPlayerException("プレイヤーが重複");
    }
    playerService.addPlayer(playerId, playerName);
  }

}
