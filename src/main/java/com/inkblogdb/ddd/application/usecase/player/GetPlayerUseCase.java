package com.inkblogdb.ddd.application.usecase.player;

import com.inkblogdb.ddd.application.dto.player.PlayerDTO;
import com.inkblogdb.ddd.application.usecase.abort.PlayerNotFondException;
import com.inkblogdb.ddd.domain.model.player.Player;
import com.inkblogdb.ddd.domain.model.player.PlayerId;
import com.inkblogdb.ddd.domain.model.player.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetPlayerUseCase {

  private final PlayerRepository playerRepository;

  public PlayerDTO getPlayer(PlayerId playerId) throws PlayerNotFondException {
    if (playerRepository.notExists(playerId)) {
      throw new PlayerNotFondException("プレイヤーが存在しない");
    }
    Player player = playerRepository.findById(playerId);
    return new PlayerDTO(
        player.playerId().value(),
        player.playerName().value()
    );
  }

}
