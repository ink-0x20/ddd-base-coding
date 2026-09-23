package com.inkblogdb.ddd.domain.model.song;

public class SongLevelNormal extends SongLevel {

  public SongLevelNormal(int value) {
    super(value);
  }

  protected String invalidErrorMessage() {
    return "楽曲レベルNORMALの形式が不正";
  }

}
