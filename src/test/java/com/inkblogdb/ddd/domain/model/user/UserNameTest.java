package com.inkblogdb.ddd.domain.model.user;

import com.inkblogdb.ddd.domain.DomainException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserNameTest {

  @Test
  void nullを渡すと例外になること() {
    // when then
    assertEquals(
        "ユーザー名がnull"
        , assertThrows(DomainException.class, () -> new UserName(null)).getMessage()
    );
  }

  @Test
  void 空白を渡すと例外になること() {
    // when then
    assertEquals(
        "ユーザー名が空白"
        , assertThrows(DomainException.class, () -> new UserName("")).getMessage()
    );
    assertEquals(
        "ユーザー名が空白"
        , assertThrows(DomainException.class, () -> new UserName(" ")).getMessage()
    );
    assertEquals(
        "ユーザー名が空白"
        , assertThrows(DomainException.class, () -> new UserName("　")).getMessage()
    );
  }

  @Test
  void 値が30桁を超えると例外になること() {
    // when then
    assertEquals(
        "ユーザー名が30桁を超えている 1234567890123456789012345678901"
        , assertThrows(DomainException.class, () -> new UserName("1234567890123456789012345678901")).getMessage()
    );
  }

  @Test
  void 値が30桁だと値が取得できること() {
    //given
    String name = "123456789012345678901234567890";

    // when
    var result = new UserName(name);

    // then
    assertEquals(name, result.value());
  }

}
