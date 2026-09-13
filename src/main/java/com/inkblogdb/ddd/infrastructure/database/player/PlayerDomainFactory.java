package com.inkblogdb.ddd.infrastructure.database.player;

import com.inkblogdb.ddd.domain.model.player.Player;
import com.inkblogdb.ddd.domain.model.player.PlayerId;
import com.inkblogdb.ddd.domain.model.player.PlayerName;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PlayerDomainFactory {

  public Optional<Player> createFrom(PlayerRecordEntity playerRecordEntity) {
    return Optional.ofNullable(playerRecordEntity)
        .map(entity -> new Player(
                new PlayerId(entity.id()),
                new PlayerName(entity.name())
            )
        );
  }

}
