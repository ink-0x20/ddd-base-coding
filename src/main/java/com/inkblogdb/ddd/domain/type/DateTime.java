package com.inkblogdb.ddd.domain.type;

import com.inkblogdb.ddd.domain.DomainException;

import java.time.LocalDateTime;

public abstract class DateTime {

  private final LocalDateTime value;

  public DateTime(LocalDateTime value) {
    if (value == null) {
      throw new DomainException(emptyErrorMessage());
    }
    this.value = value;
  }

  protected abstract String emptyErrorMessage();

  public LocalDateTime value() {
    return this.value;
  }

}
