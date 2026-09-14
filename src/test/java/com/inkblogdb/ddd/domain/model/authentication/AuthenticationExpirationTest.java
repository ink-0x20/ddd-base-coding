package com.inkblogdb.ddd.domain.model.authentication;

import com.inkblogdb.ddd.domain.DomainException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class AuthenticationExpirationTest {

  @Test
  void 認証試行日時が0以下の場合エラーになること() {
    // when then
    assertEquals(
        "認証試行日時の形式が不正",
        assertThrows(DomainException.class, () -> new AuthenticationExpiration(0, 1)).getMessage()
    );
    assertEquals(
        "認証試行日時の形式が不正",
        assertThrows(DomainException.class, () -> new AuthenticationExpiration(-1, 1)).getMessage()
    );
  }

  @Test
  void 認証有効期限が0以下の場合エラーになること() {
    // when then
    assertEquals(
        "認証有効期限の形式が不正",
        assertThrows(DomainException.class, () -> new AuthenticationExpiration(1, 0)).getMessage()
    );
    assertEquals(
        "認証有効期限の形式が不正",
        assertThrows(DomainException.class, () -> new AuthenticationExpiration(1, -1)).getMessage()
    );
  }

  @Test
  void 現在時刻が午前4時直前の場合は同日4時が期限になること() {
    // given
    ZoneId zoneId = ZoneId.systemDefault();
    Instant nowInstant = LocalDateTime.parse("2026-04-01T03:59:59.999").atZone(zoneId).toInstant();
    Clock fixedClock = Clock.fixed(nowInstant, zoneId);

    // when
    AuthenticationExpiration result = AuthenticationExpiration.until4AMTheNextTime(fixedClock);

    // then
    assertEquals(nowInstant.toEpochMilli(), result.attempt());
    assertEquals(LocalDateTime.parse("2026-04-01T04:00:00.000").atZone(zoneId).toInstant().toEpochMilli(), result.expiration());
  }

  @Test
  void 現在時刻が午前4時ぴったりの場合は翌日4時が期限になること() {
    // given
    ZoneId zoneId = ZoneId.systemDefault();
    Instant nowInstant = LocalDateTime.parse("2026-04-01T04:00:00.000").atZone(zoneId).toInstant();
    Clock fixedClock = Clock.fixed(nowInstant, zoneId);

    // when
    AuthenticationExpiration result = AuthenticationExpiration.until4AMTheNextTime(fixedClock);

    // then
    assertEquals(nowInstant.toEpochMilli(), result.attempt());
    assertEquals(LocalDateTime.parse("2026-04-02T04:00:00.000").atZone(zoneId).toInstant().toEpochMilli(), result.expiration());
  }

  @Test
  void 現在時刻が午前4時直後の場合は翌日4時が期限になること() {
    // given
    ZoneId zoneId = ZoneId.systemDefault();
    Instant nowInstant = LocalDateTime.parse("2026-04-01T04:00:00.001").atZone(zoneId).toInstant();
    Clock fixedClock = Clock.fixed(nowInstant, zoneId);

    // when
    AuthenticationExpiration result = AuthenticationExpiration.until4AMTheNextTime(fixedClock);

    // then
    assertEquals(nowInstant.toEpochMilli(), result.attempt());
    assertEquals(LocalDateTime.parse("2026-04-02T04:00:00.000").atZone(zoneId).toInstant().toEpochMilli(), result.expiration());
  }
}
