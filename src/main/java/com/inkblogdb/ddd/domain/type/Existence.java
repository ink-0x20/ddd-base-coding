package com.inkblogdb.ddd.domain.type;

public enum Existence {

  YES,
  NO;

  public boolean yes() {
    return this == YES;
  }

  public boolean no() {
    return !yes();
  }

}
