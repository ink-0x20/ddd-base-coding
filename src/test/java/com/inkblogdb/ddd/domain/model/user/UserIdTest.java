package com.inkblogdb.ddd.domain.model.user;

import com.inkblogdb.ddd.domain.DomainException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserIdTest {

  @Test
  void nullを渡すと例外になること() {
    // when then
    assertEquals(
        "ユーザーIDが空",
        assertThrows(DomainException.class, () -> new UserId(null)).getMessage()
    );
  }

  @Test
  void 空白を渡すと例外になること() {
    // when then
    assertEquals(
        "ユーザーIDが空",
        assertThrows(DomainException.class, () -> new UserId("")).getMessage()
    );
    assertEquals(
        "ユーザーIDが空",
        assertThrows(DomainException.class, () -> new UserId(" ")).getMessage()
    );
    assertEquals(
        "ユーザーIDが空",
        assertThrows(DomainException.class, () -> new UserId("　")).getMessage()
    );
  }

  @Test
  void 値が8桁でないと例外になること() {
    // when then
    assertEquals(
        "ユーザーIDの形式が不正 2345678",
        assertThrows(DomainException.class, () -> new UserId("2345678")).getMessage()
    );
    assertEquals(
        "ユーザーIDの形式が不正 23456789a",
        assertThrows(DomainException.class, () -> new UserId("23456789a")).getMessage()
    );
  }

  @Test
  void 読みにくい文字列を渡すと例外になること() {
    // when then
    assertEquals(
        "ユーザーIDの形式が不正 1l1l1l1l",
        assertThrows(DomainException.class, () -> new UserId("1l1l1l1l")).getMessage()
    );
  }

  @Test
  void 文字列を渡して生成できること() {
    // given
    String id = "23456789";

    // when
    var result = new UserId(id);

    // then
    assertEquals(id, result.value());
  }

  @Test
  void ユーザーIDが生成できること() {
    // when
    var result = UserId.generate();

    // then
    assertNotNull(result.value());
    assertEquals(8, result.value().length());
  }

}
