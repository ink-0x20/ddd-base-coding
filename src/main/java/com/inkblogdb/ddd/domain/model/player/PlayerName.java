package com.inkblogdb.ddd.domain.model.player;

import com.inkblogdb.ddd.domain.DomainException;

public record PlayerName(String value) {

  public PlayerName {
    if (value == null) {
      throw new DomainException("プレイヤー名がnull");
    }
    if (value.isBlank()) {
      throw new DomainException("プレイヤー名が空白");
    }
    if (30 < value.length()) {
      throw new DomainException("プレイヤー名が30桁を超えている " + value);
    }
  }

}
