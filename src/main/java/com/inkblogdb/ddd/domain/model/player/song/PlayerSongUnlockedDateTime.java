package com.inkblogdb.ddd.domain.model.player.song;

import com.inkblogdb.ddd.domain.type.DateTime;

import java.time.LocalDateTime;

public class PlayerSongUnlockedDateTime extends DateTime {

  public PlayerSongUnlockedDateTime(LocalDateTime value) {
    super(value);
  }

  @Override
  protected void validate(LocalDateTime value) {
  }

  public static PlayerSongUnlockedDateTime now() {
    return new PlayerSongUnlockedDateTime(LocalDateTime.now());
  }

}
