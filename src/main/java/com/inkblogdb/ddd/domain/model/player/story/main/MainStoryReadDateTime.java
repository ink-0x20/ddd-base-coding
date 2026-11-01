package com.inkblogdb.ddd.domain.model.player.story.main;

import com.inkblogdb.ddd.domain.type.DateTime;

import java.time.LocalDateTime;

public class MainStoryReadDateTime extends DateTime {

  public MainStoryReadDateTime(LocalDateTime value) {
    super(value);
  }

  @Override
  protected void validate(LocalDateTime value) {
  }

  public static MainStoryReadDateTime createUnread() {
    return new MainStoryReadDateTime(null);
  }

  public boolean isUnread() {
    return value() == null;
  }

  public boolean isRead() {
    return !isUnread();
  }

}
