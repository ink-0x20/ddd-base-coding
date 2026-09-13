package com.inkblogdb.ddd.domain.model.authentication;

import com.inkblogdb.ddd.domain.DomainException;

import java.time.*;

public record AuthenticationExpiration(long attempt, long expiration) {

  public AuthenticationExpiration {
    if (attempt < 1) {
      throw new DomainException("認証日時の形式が不正");
    }
    if (expiration < 1) {
      throw new DomainException("認証有効期限の形式が不正");
    }
  }

  public static AuthenticationExpiration until4AMTheNextTime() {
    ZonedDateTime now =ZonedDateTime.now(ZoneId.systemDefault());
    ZonedDateTime next4AM = now.with(LocalTime.of(4, 0, 0, 0));
    if (!now.isBefore(next4AM)) {
      next4AM = next4AM.plusDays(1);
    }
    return new AuthenticationExpiration(
        now.toInstant().toEpochMilli(),
        next4AM.toInstant().toEpochMilli()
    );
  }

}
