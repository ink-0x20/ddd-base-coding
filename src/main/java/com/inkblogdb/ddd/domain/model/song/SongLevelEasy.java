package com.inkblogdb.ddd.domain.model.song;

public class SongLevelEasy extends SongLevel {

  public SongLevelEasy(int value) {
    super(value);
  }

  protected String invalidErrorMessage() {
    return "楽曲レベルEASYの形式が不正";
  }

}
