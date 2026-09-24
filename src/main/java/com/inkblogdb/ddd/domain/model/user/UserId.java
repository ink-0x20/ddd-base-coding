package com.inkblogdb.ddd.domain.model.user;

import com.inkblogdb.ddd.domain.DomainException;
import lombok.NonNull;

import java.util.UUID;

public record UserId(UUID value) {

  public UserId {
    if (value == null) {
      throw new DomainException("ユーザーIDが空");
    }
    if (value.version() != 4) {
      throw new DomainException("ユーザーIDの形式が不正 " + value);
    }
  }

  public UserId(String value) {
    if (value == null || value.isBlank()) {
      throw new DomainException("ユーザーIDが空");
    }
    UUID v;
    try {
      v = UUID.fromString(value);
      if (v.version() != 4) {
        throw new DomainException("ユーザーIDの形式が不正 " + value);
      }
    } catch (Exception e) {
      throw new DomainException("ユーザーIDの形式が不正 " + value);
    }
    this(v);
  }

  @NonNull
  @Override
  public String toString() {
    return value.toString();
  }

}
