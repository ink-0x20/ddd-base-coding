package com.inkblogdb.ddd.domain.model.player.song.unlock;

import com.inkblogdb.ddd.domain.DomainException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Arrays;

@Getter
@Accessors(fluent = true)
@RequiredArgsConstructor
public enum SongUnlockStatus {

  UNLOCKED(0),
  UNLOCKABLE_BY_GOLD(1),
  UNLOCKABLE_BY_STORY(2)
  ;

  private final int value;

  public static SongUnlockStatus createFrom(int value) {
    return Arrays.stream(values())
        .filter(status -> status.value() == value)
        .findFirst()
        .orElseThrow(() -> new DomainException("楽曲解放状態の形式が不正"));
  }

}
