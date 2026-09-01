package com.inkblogdb.ddd.domain.model.user;

import com.inkblogdb.ddd.domain.DomainException;
import lombok.NonNull;

import java.util.UUID;

public record UserId(UUID value) {

  public UserId {
    if (value == null) {
      throw new DomainException("ユーザーIDがnull");
    }
  }

  public UserId(String value) {
    if (value == null) {
      throw new DomainException("ユーザーIDがnull");
    }
    if (value.isBlank()) {
      throw new DomainException("ユーザーIDが空白");
    }
    if (value.length() != 36) {
      throw new DomainException("ユーザーIDが36桁ではない " + value);
    }
    if (!value.matches("^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$")) {
      throw new DomainException("ユーザーIDの形式が不正 " + value);
    }
    this(UUID.fromString(value));
  }

  public static UserId generate() {
    return new UserId(UUID.randomUUID());
  }

  @NonNull
  @Override
  public String toString() {
    return value.toString();
  }

}
