package com.inkblogdb.ddd.domain.model.player;

import com.inkblogdb.ddd.domain.DomainException;
import com.inkblogdb.ddd.domain.type.Text;

public class PlayerName extends Text {

  public PlayerName(String value) {
    super(value);
  }

  @Override
  protected void validate(String value) {
    if (value == null || value.isBlank()) {
      throw new DomainException("プレイヤー名が空");
    }
    if (30 < value.length()) {
      throw new DomainException("プレイヤー名が30桁を超えている " + value);
    }
  }

}
