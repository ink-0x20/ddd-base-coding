package com.inkblogdb.ddd.domain.model.song;

import com.inkblogdb.ddd.domain.DomainException;
import lombok.NonNull;

import java.util.UUID;

public record SongUnlockTargetId(UUID value) {

  public SongUnlockTargetId {
    if (value != null && value.version() != 4) {
      throw new DomainException("楽曲解放対象IDの形式が不正 " + value);
    }
  }

  public SongUnlockTargetId(String value) {
    UUID v = null;
    if (value != null) {
      try {
        v = UUID.fromString(value);
        if (v.version() != 4) {
          throw new DomainException("楽曲解放対象IDの形式が不正 " + value);
        }
      } catch (Exception e) {
        throw new DomainException("楽曲解放対象IDの形式が不正 " + value);
      }
    }
    this(v);
  }

  @NonNull
  @Override
  public String toString() {
    return value == null ? "" : value.toString();
  }

}
