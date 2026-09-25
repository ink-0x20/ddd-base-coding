package com.inkblogdb.ddd.domain.model.song;

import com.inkblogdb.ddd.domain.DomainException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Arrays;

@Getter
@Accessors(fluent = true)
@RequiredArgsConstructor
public enum SongUnlockType {

  FREE(0),
  TICKET(1);

  private final int value;

  public static SongUnlockType createFrom(int value) {
    return Arrays.stream(values())
        .filter(status -> status.value() == value)
        .findFirst()
        .orElseThrow(() -> new DomainException("楽曲解放条件の形式が不正"));
  }

  public boolean free() {
    return this == FREE;
  }

  public boolean ticket() {
    return this == TICKET;
  }

}
