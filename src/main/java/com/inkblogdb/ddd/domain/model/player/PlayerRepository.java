package com.inkblogdb.ddd.domain.model.player;

public interface PlayerRepository {

  boolean exists(PlayerId playerId);

  boolean notExists(PlayerId playerId);

  Player findById(PlayerId playerId);

  void addPlayer(PlayerId playerId, PlayerName playerName);

}
