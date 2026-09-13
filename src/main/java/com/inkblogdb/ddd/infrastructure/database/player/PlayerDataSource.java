package com.inkblogdb.ddd.infrastructure.database.player;

import com.inkblogdb.ddd.domain.model.player.Player;
import com.inkblogdb.ddd.domain.model.player.PlayerId;
import com.inkblogdb.ddd.domain.model.player.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PlayerDataSource implements PlayerRepository {

  private final PlayerMapper playerMapper;
  private final PlayerDomainFactory playerDomainFactory;

  public Optional<Player> findById(PlayerId playerId) {
    return playerDomainFactory.createFrom(playerMapper.findById(playerId.value()));
  }

  public void save(Player player) {
    PlayerRecordEntity entity = new PlayerRecordEntity(
        player.playerId().value(),
        player.playerName().value()
    );
    playerMapper.save(entity);
  }

}
