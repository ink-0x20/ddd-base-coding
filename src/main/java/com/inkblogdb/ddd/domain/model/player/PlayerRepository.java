package com.inkblogdb.ddd.domain.model.player;

import java.util.Optional;

public interface PlayerRepository {

  Optional<Player> findById(PlayerId playerId);

  void save(Player player);

}
