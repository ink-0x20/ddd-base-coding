package com.inkblogdb.ddd.domain.model.item;

import com.inkblogdb.ddd.domain.DomainException;
import lombok.NonNull;

import java.util.UUID;

public record ItemId(UUID value) {

  public ItemId {
    if (value == null) {
      throw new DomainException("アイテムIDが空");
    }
    if (value.version() != 4) {
      throw new DomainException("アイテムIDの形式が不正 " + value);
    }
  }

  public ItemId(String value) {
    if (value == null || value.isBlank()) {
      throw new DomainException("アイテムIDが空");
    }
    UUID v;
    try {
      v = UUID.fromString(value);
      if (v.version() != 4) {
        throw new DomainException("アイテムIDの形式が不正 " + value);
      }
    } catch (Exception e) {
      throw new DomainException("アイテムIDの形式が不正 " + value);
    }
    this(v);
  }

  @NonNull
  @Override
  public String toString() {
    return value.toString();
  }

}
