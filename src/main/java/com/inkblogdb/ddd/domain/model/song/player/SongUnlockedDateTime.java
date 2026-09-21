package com.inkblogdb.ddd.domain.model.song.player;

import com.inkblogdb.ddd.domain.type.DateTime;

import java.time.LocalDateTime;

public class SongUnlockedDateTime extends DateTime {

  public SongUnlockedDateTime(LocalDateTime value) {
    super(value);
  }

  @Override
  protected String emptyErrorMessage() {
    return "楽曲解放日時が空";
  }

  public static SongUnlockedDateTime now() {
    return new SongUnlockedDateTime(LocalDateTime.now());
  }

}
