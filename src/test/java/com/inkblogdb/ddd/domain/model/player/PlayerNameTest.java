package com.inkblogdb.ddd.domain.model.player;

import com.inkblogdb.ddd.domain.DomainException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PlayerNameTest {

  @Test
  void nullを渡すと例外になること() {
    // when then
    assertEquals(
        "プレイヤー名がnull",
        assertThrows(DomainException.class, () -> new PlayerName(null)).getMessage()
    );
  }

  @Test
  void 空白を渡すと例外になること() {
    // when then
    assertEquals(
        "プレイヤー名が空白",
        assertThrows(DomainException.class, () -> new PlayerName("")).getMessage()
    );
    assertEquals(
        "プレイヤー名が空白",
        assertThrows(DomainException.class, () -> new PlayerName(" ")).getMessage()
    );
    assertEquals(
        "プレイヤー名が空白",
        assertThrows(DomainException.class, () -> new PlayerName("　")).getMessage()
    );
  }

  @Test
  void 値が30桁を超えると例外になること() {
    // when then
    assertEquals(
        "プレイヤー名が30桁を超えている 1234567890123456789012345678901",
        assertThrows(DomainException.class, () -> new PlayerName("1234567890123456789012345678901")).getMessage()
    );
  }

  @Test
  void 値が30桁だと値が取得できること() {
    //given
    String name = "123456789012345678901234567890";

    // when
    var result = new PlayerName(name);

    // then
    assertEquals(name, result.value());
  }

}
