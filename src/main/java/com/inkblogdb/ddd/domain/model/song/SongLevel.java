package com.inkblogdb.ddd.domain.model.song;

import com.inkblogdb.ddd.domain.DomainException;

public record SongLevel(int value) {

  public SongLevel {
    if (value < 1) {
      throw new DomainException("楽曲レベルの形式が不正");
    }
  }

}
