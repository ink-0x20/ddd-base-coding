package com.inkblogdb.ddd.domain.model.song;

import com.inkblogdb.ddd.domain.type.Identifier;

public class SongId extends Identifier {

  public SongId(String value) {
    super(value);
  }

  @Override
  protected String emptyErrorMessage() {
    return "楽曲IDが空";
  }

  @Override
  protected String invalidErrorMessage(String value) {
    return "楽曲IDの形式が不正 " + value;
  }

  public static SongId generate() {
    return new SongId(random());
  }

}
