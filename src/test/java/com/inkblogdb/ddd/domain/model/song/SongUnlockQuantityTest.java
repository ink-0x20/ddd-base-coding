package com.inkblogdb.ddd.domain.model.song;

import com.inkblogdb.ddd.domain.DomainException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SongUnlockQuantityTest {

  @Test
  void 値が負の整数の場合例外になること() {
    // when then
    assertEquals(
        "楽曲解放に必要な数の形式が不正",
        assertThrows(DomainException.class, () -> new SongUnlockQuantity(-1)).getMessage()
    );
  }

  @Test
  void 値が8桁以上の場合例外になること() {
    // when then
    assertEquals(
        "楽曲解放に必要な数の形式が不正",
        assertThrows(DomainException.class, () -> new SongUnlockQuantity(10000000)).getMessage()
    );
  }

  @Test
  void 値0の場合生成できること() {
    // given
    int value = 0;

    // when
    var result = new SongUnlockQuantity(value);

    // then
    assertEquals(value, result.value());
  }

  @Test
  void 値が9999999の場合生成できること() {
    // given
    int value = 9999999;

    // when
    var result = new SongUnlockQuantity(value);

    // then
    assertEquals(value, result.value());
  }

}
