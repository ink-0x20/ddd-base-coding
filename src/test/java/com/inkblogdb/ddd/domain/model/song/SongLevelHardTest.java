package com.inkblogdb.ddd.domain.model.song;

import com.inkblogdb.ddd.domain.DomainException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SongLevelHardTest {

  @Test
  void 値が負の整数の場合例外になること() {
    // when then
    assertEquals(
        "楽曲レベルHARDの形式が不正",
        assertThrows(DomainException.class, () -> new SongLevelHard(-1)).getMessage()
    );
  }

  @Test
  void 値が0の場合例外になること() {
    // when then
    assertEquals(
        "楽曲レベルHARDの形式が不正",
        assertThrows(DomainException.class, () -> new SongLevelHard(0)).getMessage()
    );
  }

  @Test
  void 値が3桁以上の場合例外になること() {
    // when then
    assertEquals(
        "楽曲レベルHARDの形式が不正",
        assertThrows(DomainException.class, () -> new SongLevelHard(100)).getMessage()
    );
  }

  @Test
  void 値1の場合生成できること() {
    // given
    int level = 1;

    // when
    var result = new SongLevelHard(level);

    // then
    assertEquals(level, result.value());
  }

  @Test
  void 値が99の場合生成できること() {
    // given
    int level = 99;

    // when
    var result = new SongLevelHard(level);

    // then
    assertEquals(level, result.value());
  }

}
