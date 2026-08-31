package com.inkblogdb.ddd.domain.model.user;

import com.inkblogdb.ddd.domain.DomainException;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserIdTest {

  @Test
  void nullを渡すと例外になること() {
    // when then
    assertEquals(
        "ユーザーIDがnull"
        , assertThrows(DomainException.class, () -> new UserId(null)).getMessage()
    );
  }

  @Test
  void 空白を渡すと例外になること() {
    // when then
    assertEquals(
        "ユーザーIDが空白"
        , assertThrows(DomainException.class, () -> new UserId("")).getMessage()
    );
    assertEquals(
        "ユーザーIDが空白"
        , assertThrows(DomainException.class, () -> new UserId(" ")).getMessage()
    );
    assertEquals(
        "ユーザーIDが空白"
        , assertThrows(DomainException.class, () -> new UserId("　")).getMessage()
    );
  }

  @Test
  void 値が36桁でないと例外になること() {
    // when then
    assertEquals(
        "ユーザーIDが36桁ではない a"
        , assertThrows(DomainException.class, () -> new UserId("a")).getMessage()
    );
  }

  @Test
  void UUIDの形式でないと例外になること() {
    // when then
    assertEquals(
        "ユーザーIDの形式が不正 123456789012345678901234567890123456"
        , assertThrows(DomainException.class, () -> new UserId("123456789012345678901234567890123456")).getMessage()
    );
  }

  @Test
  void UUIDv4の形式だと値が取得できること() {
    //given
    String uuid = UUID.randomUUID().toString();

    // when
    var result = new UserId(uuid);

    // then
    assertEquals(uuid, result.value());
  }

}
