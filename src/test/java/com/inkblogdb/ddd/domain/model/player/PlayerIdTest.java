package com.inkblogdb.ddd.domain.model.player;

import com.inkblogdb.ddd.domain.DomainException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerIdTest {

  @Test
  void nullを渡すと例外になること() {
    // when then
    assertEquals(
        "プレイヤーIDが空",
        assertThrows(DomainException.class, () -> new PlayerId(null)).getMessage()
    );
  }

  @Test
  void 空白を渡すと例外になること() {
    // when then
    assertEquals(
        "プレイヤーIDが空",
        assertThrows(DomainException.class, () -> new PlayerId("")).getMessage()
    );
    assertEquals(
        "プレイヤーIDが空",
        assertThrows(DomainException.class, () -> new PlayerId(" ")).getMessage()
    );
    assertEquals(
        "プレイヤーIDが空",
        assertThrows(DomainException.class, () -> new PlayerId("　")).getMessage()
    );
  }

  @Test
  void 値が8桁でないと例外になること() {
    // when then
    assertEquals(
        "プレイヤーIDの形式が不正 2345678",
        assertThrows(DomainException.class, () -> new PlayerId("2345678")).getMessage()
    );
    assertEquals(
        "プレイヤーIDの形式が不正 23456789a",
        assertThrows(DomainException.class, () -> new PlayerId("23456789a")).getMessage()
    );
  }

  @Test
  void 読みにくい文字列を渡すと例外になること() {
    // when then
    assertEquals(
        "プレイヤーIDの形式が不正 1l1l1l1l",
        assertThrows(DomainException.class, () -> new PlayerId("1l1l1l1l")).getMessage()
    );
  }

  @Test
  void 文字列を渡して生成できること() {
    // given
    String id = "23456789";

    // when
    var result = new PlayerId(id);

    // then
    assertEquals(id, result.value());
  }

  @Test
  void プレイヤーIDが生成できること() {
    // when
    var result = PlayerId.generate();

    // then
    assertNotNull(result.value());
    assertEquals(8, result.value().length());
  }

}
