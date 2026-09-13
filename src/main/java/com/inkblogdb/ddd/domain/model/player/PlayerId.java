package com.inkblogdb.ddd.domain.model.player;

import com.inkblogdb.ddd.domain.type.Identifier;

public class PlayerId extends Identifier {

  public PlayerId(String value) {
    super(value);
  }

  @Override
  protected String emptyErrorMessage() {
    return "プレイヤーIDが空";
  }

  @Override
  protected String invalidErrorMessage(String value) {
    return "プレイヤーIDの形式が不正 " + value;
  }

  public static PlayerId generate() {
    return new PlayerId(random());
  }

}
