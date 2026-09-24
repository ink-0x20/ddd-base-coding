package com.inkblogdb.ddd.domain.model.song;

public class SongLevelEasy extends SongLevel {

  public SongLevelEasy(int value) {
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
    return "楽曲レベルEASYの形式が不正";
  }

}
