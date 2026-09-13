package com.inkblogdb.ddd.infrastructure.database.player;

import com.inkblogdb.ddd.domain.model.player.Player;
import com.inkblogdb.ddd.domain.model.player.PlayerId;
import com.inkblogdb.ddd.domain.model.player.PlayerName;
import com.inkblogdb.ddd.domain.model.player.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PlayerDataSource implements PlayerRepository {

  private final PlayerMapper playerMapper;
  private final PlayerDomainFactory playerDomainFactory;

  @Override
  public boolean exists(PlayerId playerId) {
    return playerMapper.exists(playerId.value());
  }

  @Override
  public boolean notExists(PlayerId playerId) {
    return !exists(playerId);
  }

  @Override
  public Player findById(PlayerId playerId) {
    return playerDomainFactory.createFrom(playerMapper.findById(playerId.value()));
  }

  @Override
  public void addPlayer(PlayerId playerId, PlayerName playerName) {
    PlayerRecordEntity entity = new PlayerRecordEntity(
        playerId.value(),
        playerName.value()
    );
    playerMapper.addPlayer(entity);
  }

}
