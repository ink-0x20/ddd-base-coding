package com.inkblogdb.ddd.domain.model.player.item;

import com.inkblogdb.ddd.domain.type.Whole;

public class PlayerItemQuantity extends Whole {

  public PlayerItemQuantity(int value) {
    super(value);
  }

  @Override
  protected int min() {
    return 0;
  }

  @Override
  protected int max() {
    return 9999999;
  }

  @Override
  protected String invalidErrorMessage() {
    return "アイテム所持数の形式が不正";
  }

  public PlayerItemQuantity minus(Whole quantity) {
    return new PlayerItemQuantity(this.value() - quantity.value());
  }

}
