package com.inkblogdb.ddd.domain.model.player.song.unlock;

import com.inkblogdb.ddd.domain.DomainException;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.util.Arrays;

@Getter
@Accessors(fluent = true)
public enum PlayerSongUnlockAction {

  UNLOCK_BY_TICKET;

  public static PlayerSongUnlockAction createFrom(String value) {
    return Arrays.stream(values())
        .filter(action -> action.name().equals(value))
        .findFirst()
        .orElseThrow(() -> new DomainException("楽曲解放操作の形式が不正"));
  }

}
