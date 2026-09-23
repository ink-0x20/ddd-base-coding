package com.inkblogdb.ddd.domain.model.song;

import com.inkblogdb.ddd.domain.DomainException;
import com.inkblogdb.ddd.domain.type.Text;

public class SongName extends Text {

  public SongName(String value) {
    super(value);
  }

  @Override
  protected void validate(String value) {
    if (value == null || value.isBlank()) {
      throw new DomainException("楽曲名が空");
    }
    if (50 < value.length()) {
      throw new DomainException("楽曲名が50桁を超えている " + value);
    }
  }

}
