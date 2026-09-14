package com.inkblogdb.ddd.domain.model.player;

import com.inkblogdb.ddd.domain.type.Exists;

public interface PlayerRepository {

  Exists exists(PlayerId playerId);

  Player findById(PlayerId playerId);

  void addPlayer(PlayerId playerId, PlayerName playerName);

}
