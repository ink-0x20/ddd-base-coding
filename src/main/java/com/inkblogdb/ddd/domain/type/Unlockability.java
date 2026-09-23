package com.inkblogdb.ddd.domain.type;

public enum Unlockability {

  UNLOCKABLE,
  LOCKED;

  public boolean canUnlock() {
    return this == UNLOCKABLE;
  }

  public boolean isLocked() {
    return !canUnlock();
  }

}
