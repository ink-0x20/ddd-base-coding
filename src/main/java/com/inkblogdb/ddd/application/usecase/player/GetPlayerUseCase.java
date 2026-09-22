package com.inkblogdb.ddd.application.usecase.player;

import com.inkblogdb.ddd.application.usecase.abort.not_found.PlayerNotFoundException;
import com.inkblogdb.ddd.domain.model.player.Player;
import com.inkblogdb.ddd.domain.model.player.PlayerId;
import com.inkblogdb.ddd.domain.model.player.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class GetPlayerUseCase {

  private final PlayerRepository playerRepository;

  @Transactional(isolation = Isolation.READ_COMMITTED)
  public GetPlayerModel getPlayer(GetPlayerCommand getPlayerCommand) throws PlayerNotFoundException {
    PlayerId playerId = new PlayerId(getPlayerCommand.playerId());

    // プレイヤー重複チェック
    if (playerRepository.exists(playerId).no()) {
      throw new PlayerNotFoundException("プレイヤーが存在しない");
    }

    // プレイヤー取得
    Player player = playerRepository.findById(playerId);
    return new GetPlayerModel(
        player.playerId().value(),
        player.playerName().value()
    );
  }

}
