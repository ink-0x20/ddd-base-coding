package com.inkblogdb.ddd.domain.model.song;

import com.inkblogdb.ddd.domain.DomainException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SongLevelTest {

  @Test
  void 値が0の場合例外になること() {
    // when then
    assertEquals(
        "楽曲レベルの形式が不正",
        assertThrows(DomainException.class, () -> new SongLevel(0)).getMessage()
    );
  }

  @Test
  void 値が負の整数の場合例外になること() {
    // when then
    assertEquals(
        "楽曲レベルの形式が不正",
        assertThrows(DomainException.class, () -> new SongLevel(-1)).getMessage()
    );
  }

  @Test
  void 値が正の整数の場合例外になること() {
    // given
    int level = 1;

    // when
    var result = new SongLevel(level);

    // then
    assertEquals(level, result.value());
  }

}
