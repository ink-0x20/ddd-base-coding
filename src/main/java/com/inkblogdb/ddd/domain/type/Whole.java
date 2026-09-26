package com.inkblogdb.ddd.domain.type;

import com.inkblogdb.ddd.domain.DomainException;

public abstract class Whole {

  private final int value;

  public Whole(int value) {
    if (value < min() || max() < value) {
      throw new DomainException(invalidErrorMessage());
    }
    this.value = value;
  }

  protected abstract int min();

  protected abstract int max();

  protected abstract String invalidErrorMessage();

  public int value() {
    return this.value;
  }

  public boolean isGreaterThanOrEqual(Whole other) {
    if (other == null) {
      return false;
    }
    return this.value >= other.value;
  }

}
