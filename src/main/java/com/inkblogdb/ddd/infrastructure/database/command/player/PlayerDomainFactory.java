package com.inkblogdb.ddd.infrastructure.database.command.player;

import com.inkblogdb.ddd.domain.model.player.Player;
import com.inkblogdb.ddd.domain.model.player.PlayerId;
import com.inkblogdb.ddd.domain.model.player.PlayerName;
import com.inkblogdb.ddd.infrastructure.InfrastructureException;
import org.springframework.stereotype.Component;

@Component
public class PlayerDomainFactory {

  public Player createFrom(PlayerRecordEntity playerRecordEntity) {
    if (playerRecordEntity == null) {
      throw new InfrastructureException("プレイヤーレコードが存在しない");
    }
    return new Player(
        new PlayerId(playerRecordEntity.id()),
        new PlayerName(playerRecordEntity.name())
    );
  }

}
