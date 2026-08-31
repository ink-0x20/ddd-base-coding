package com.inkblogdb.ddd.domain.model.user;

import com.inkblogdb.ddd.domain.DomainException;

public record UserId(String value) {

  public UserId {
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
  }

}
