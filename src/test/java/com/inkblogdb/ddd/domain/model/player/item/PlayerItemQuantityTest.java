package com.inkblogdb.ddd.domain.model.player.item;

import com.inkblogdb.ddd.domain.DomainException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerItemQuantityTest {

  @Test
  void 値が負の整数の場合例外になること() {
    // when then
    assertEquals(
        "アイテム所持数の形式が不正",
        assertThrows(DomainException.class, () -> new PlayerItemQuantity(-1)).getMessage()
    );
  }

  @Test
  void 値が8桁以上の場合例外になること() {
    // when then
    assertEquals(
        "アイテム所持数の形式が不正",
        assertThrows(DomainException.class, () -> new PlayerItemQuantity(10000000)).getMessage()
    );
  }

  @Test
  void 値0の場合生成できること() {
    // given
    int level = 0;

    // when
    var result = new PlayerItemQuantity(level);

    // then
    assertEquals(level, result.value());
  }

  @Test
  void 値が9999999の場合生成できること() {
    // given
    int level = 9999999;

    // when
    var result = new PlayerItemQuantity(level);

    // then
    assertEquals(level, result.value());
  }

}
