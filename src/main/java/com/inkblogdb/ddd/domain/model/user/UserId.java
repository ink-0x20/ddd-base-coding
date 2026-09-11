package com.inkblogdb.ddd.domain.model.user;

import com.inkblogdb.ddd.domain.type.Identifier;

public class UserId extends Identifier {

  public UserId(String value) {
    super(value);
  }

  @Override
  protected String emptyErrorMessage() {
    return "ユーザーIDが空";
  }

  @Override
  protected String invalidErrorMessage(String value) {
    return "ユーザーIDの形式が不正 " + value;
  }

  public static UserId generate() {
    return new UserId(random());
  }

}
