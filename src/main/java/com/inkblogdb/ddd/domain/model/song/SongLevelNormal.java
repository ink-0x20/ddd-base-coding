package com.inkblogdb.ddd.domain.model.song;

public class SongLevelNormal extends SongLevel {

  public SongLevelNormal(int value) {
    super(value);
  }

  @Override
  protected int min() {
    return 1;
  }

  @Override
  protected int max() {
    return 99;
  }

  protected String invalidErrorMessage() {
    return "楽曲レベルNORMALの形式が不正";
  }

}
