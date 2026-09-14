package com.inkblogdb.ddd.domain.type;

public enum Exists {

  EXISTS,
  NOT_EXISTS;

  public boolean isExists() {
    return this == EXISTS;
  }

  public boolean isNotExists() {
    return !isExists();
  }

}
