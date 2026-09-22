package com.inkblogdb.ddd.domain.type;

import java.time.LocalDateTime;

public abstract class DateTime {

  private final LocalDateTime value;

  public DateTime(LocalDateTime value) {
    validate(value);
    this.value = value;
  }

  protected abstract void validate(LocalDateTime value);

  public LocalDateTime value() {
    return this.value;
  }

}
