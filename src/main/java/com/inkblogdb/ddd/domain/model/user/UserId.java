package com.inkblogdb.ddd.domain.model.user;

import com.inkblogdb.ddd.domain.DomainException;
import lombok.NonNull;

import java.util.UUID;

public record UserId(UUID value) {

  public UserId {
    if (value == null) {
      throw new DomainException("ユーザーIDが空");
    }
  }

  public UserId(String value) {
    if (value == null || value.isBlank()) {
      throw new DomainException("ユーザーIDが空");
    }
    if (!value.matches("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$")) {
      throw new DomainException("ユーザーIDの形式が不正 " + value);
    }
    this(UUID.fromString(value));
  }

  @NonNull
  @Override
  public String toString() {
    return value.toString();
  }

}
