package com.inkblogdb.ddd.domain.model.story;

import com.inkblogdb.ddd.domain.DomainException;
import lombok.NonNull;

import java.util.UUID;

public record StoryId(UUID value) {

  public StoryId {
    if (value == null) {
      throw new DomainException("ストーリーIDが空");
    }
    if (value.version() != 4) {
      throw new DomainException("ストーリーIDの形式が不正 " + value);
    }
  }

  public StoryId(String value) {
    if (value == null || value.isBlank()) {
      throw new DomainException("ストーリーIDが空");
    }
    UUID v;
    try {
      v = UUID.fromString(value);
      if (v.version() != 4) {
        throw new DomainException("ストーリーIDの形式が不正 " + value);
      }
    } catch (Exception e) {
      throw new DomainException("ストーリーIDの形式が不正 " + value);
    }
    this(v);
  }

  @NonNull
  @Override
  public String toString() {
    return value.toString();
  }

}
