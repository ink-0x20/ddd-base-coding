package com.inkblogdb.ddd.domain.model.song;

import com.inkblogdb.ddd.domain.DomainException;

import java.util.Arrays;

public enum SongUnlockStatus {

  UNLOCKED(0),
  UNLOCKABLE_BY_GOLD(1),
  UNLOCKABLE_BY_STORY(2)
  ;

  private final int value;

  SongUnlockStatus(int value) {
    this.value = value;
  }

  public int value() {
    return this.value;
  }

  public static SongUnlockStatus createFrom(int value) {
    return Arrays.stream(values())
        .filter(status -> status.value() == value)
        .findFirst()
        .orElseThrow(() -> new DomainException("楽曲解放状態の形式が不正"));
  }

}
