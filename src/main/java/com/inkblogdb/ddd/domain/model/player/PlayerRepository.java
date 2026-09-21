package com.inkblogdb.ddd.domain.model.player;

import com.inkblogdb.ddd.domain.type.Existence;

public interface PlayerRepository {

  Existence exists(PlayerId playerId);

  Player findById(PlayerId playerId);

  void addPlayer(PlayerId playerId, PlayerName playerName);

}
