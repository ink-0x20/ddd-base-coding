package com.inkblogdb.ddd.domain.model.song;

import com.inkblogdb.ddd.domain.type.Whole;

public class SongUnlockQuantity extends Whole {

  public SongUnlockQuantity(int value) {
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
    return "楽曲解放に必要な数の形式が不正";
  }
}
