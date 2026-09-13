package com.inkblogdb.ddd.infrastructure.database.player;

import com.inkblogdb.ddd.domain.model.player.Player;
import com.inkblogdb.ddd.domain.model.player.PlayerId;
import com.inkblogdb.ddd.domain.model.player.PlayerName;
import org.springframework.stereotype.Component;

@Component
public class PlayerDomainFactory {

  public Player createFrom(PlayerRecordEntity playerRecordEntity) {
    return new Player(
        new PlayerId(playerRecordEntity.id()),
        new PlayerName(playerRecordEntity.name())
    );
  }

}
