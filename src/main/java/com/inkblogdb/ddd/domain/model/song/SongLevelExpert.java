package com.inkblogdb.ddd.domain.model.song;

public class SongLevelExpert extends SongLevel {

  public SongLevelExpert(int value) {
    super(value);
  }

  protected String invalidErrorMessage() {
    return "楽曲レベルEXPERTの形式が不正";
  }

}
