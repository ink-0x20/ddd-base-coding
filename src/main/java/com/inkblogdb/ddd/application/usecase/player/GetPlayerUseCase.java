package com.inkblogdb.ddd.application.usecase.player;

import com.inkblogdb.ddd.application.usecase.abort.PlayerNotFoundException;
import com.inkblogdb.ddd.domain.model.player.Player;
import com.inkblogdb.ddd.domain.model.player.PlayerId;
import com.inkblogdb.ddd.domain.model.player.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetPlayerUseCase {

  private final PlayerRepository playerRepository;

  public GetPlayerModel getPlayer(GetPlayerCommand getPlayerCommand) throws PlayerNotFoundException {
    PlayerId playerId = new PlayerId(getPlayerCommand.playerId());
    if (playerRepository.exists(playerId).no()) {
      throw new PlayerNotFoundException("プレイヤーが存在しない");
    }
    Player player = playerRepository.findById(playerId);
    return new GetPlayerModel(
        player.playerId().value(),
        player.playerName().value()
    );
  }

}
