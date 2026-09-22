package com.inkblogdb.ddd.domain.type;

import com.inkblogdb.ddd.domain.DomainException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Arrays;

@Getter
@Accessors(fluent = true)
@RequiredArgsConstructor
public enum UnlockStatus {

  LOCKED(0),
  UNLOCKED(1);

  private final int value;

  public static UnlockStatus createFrom(int value) {
    return Arrays.stream(values())
        .filter(status -> status.value() == value)
        .findFirst()
        .orElseThrow(() -> new DomainException("楽曲解放状態の形式が不正"));
  }

  public boolean unlocked() {
    return this == UNLOCKED;
  }

  public boolean locked() {
    return !unlocked();
  }

}
