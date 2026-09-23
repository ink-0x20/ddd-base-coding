package com.inkblogdb.ddd.domain.model.song;

import com.inkblogdb.ddd.domain.type.DateTime;

import java.time.LocalDateTime;

public class SongReleaseDateTime extends DateTime {

  public SongReleaseDateTime(LocalDateTime value) {
    super(value);
  }

  @Override
  protected void validate(LocalDateTime value) {
  }

}
