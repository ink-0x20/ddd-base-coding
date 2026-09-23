package com.inkblogdb.ddd.domain.model.song;

public class SongLevelHard extends SongLevel {

  public SongLevelHard(int value) {
    super(value);
  }

  protected String invalidErrorMessage() {
    return "楽曲レベルHARDの形式が不正";
  }

}
