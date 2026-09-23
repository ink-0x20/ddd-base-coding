package com.inkblogdb.ddd.domain.model.song;

import com.inkblogdb.ddd.domain.DomainException;

public abstract class SongLevel {

  private final int value;

  public SongLevel(int value) {
    if (value < 1 || 99 < value) {
      throw new DomainException(invalidErrorMessage());
    }
    this.value = value;
  }

  protected abstract String invalidErrorMessage();

  public int value() {
    return this.value;
  }

}
