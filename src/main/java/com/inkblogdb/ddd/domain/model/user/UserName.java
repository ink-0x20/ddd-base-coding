package com.inkblogdb.ddd.domain.model.user;

import com.inkblogdb.ddd.domain.DomainException;

public record UserName(String value) {

  public UserName {
    if (value == null) {
      throw new DomainException("ユーザー名がnull");
    }
    if (value.isBlank()) {
      throw new DomainException("ユーザー名が空白");
    }
    if (30 < value.length()) {
      throw new DomainException("ユーザー名が30桁を超えている " + value);
    }
  }

}
