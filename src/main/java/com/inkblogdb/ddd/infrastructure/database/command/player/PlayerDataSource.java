package com.inkblogdb.ddd.infrastructure.database.command.player;

import com.inkblogdb.ddd.domain.model.player.Player;
import com.inkblogdb.ddd.domain.model.player.PlayerId;
import com.inkblogdb.ddd.domain.model.player.PlayerName;
import com.inkblogdb.ddd.domain.model.player.PlayerRepository;
import com.inkblogdb.ddd.domain.type.Exists;
import com.inkblogdb.ddd.infrastructure.InfrastructureException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PlayerDataSource implements PlayerRepository {

  private final PlayerMapper playerMapper;
  private final PlayerDomainFactory playerDomainFactory;

  @Override
  public Exists exists(PlayerId playerId) {
    return playerMapper.exists(playerId.value());
  }

  @Override
  public Player findById(PlayerId playerId) {
    return playerDomainFactory.createFrom(
        playerMapper.findById(playerId.value())
    );
  }

  @Override
  public void addPlayer(PlayerId playerId, PlayerName playerName) {
    PlayerRecordEntity entity = new PlayerRecordEntity(
        playerId.value(),
        playerName.value()
    );
    try {
      playerMapper.addPlayer(entity);
    } catch (Exception e) {
      throw new InfrastructureException(e);
    }
  }

}
