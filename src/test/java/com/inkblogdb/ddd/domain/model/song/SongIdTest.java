package com.inkblogdb.ddd.domain.model.song;

import com.inkblogdb.ddd.domain.DomainException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SongIdTest {

  @Test
  void nullを渡すと例外になること() {
    // when then
    assertEquals(
        "楽曲IDが空",
        assertThrows(DomainException.class, () -> new SongId(null)).getMessage()
    );
  }

  @Test
  void 空白を渡すと例外になること() {
    // when then
    assertEquals(
        "楽曲IDが空",
        assertThrows(DomainException.class, () -> new SongId("")).getMessage()
    );
    assertEquals(
        "楽曲IDが空",
        assertThrows(DomainException.class, () -> new SongId(" ")).getMessage()
    );
    assertEquals(
        "楽曲IDが空",
        assertThrows(DomainException.class, () -> new SongId("　")).getMessage()
    );
  }

  @Test
  void 値が8桁でないと例外になること() {
    // when then
    assertEquals(
        "楽曲IDの形式が不正 2345678",
        assertThrows(DomainException.class, () -> new SongId("2345678")).getMessage()
    );
    assertEquals(
        "楽曲IDの形式が不正 23456789a",
        assertThrows(DomainException.class, () -> new SongId("23456789a")).getMessage()
    );
  }

  @Test
  void 読みにくい文字列を渡すと例外になること() {
    // when then
    assertEquals(
        "楽曲IDの形式が不正 1l1l1l1l",
        assertThrows(DomainException.class, () -> new SongId("1l1l1l1l")).getMessage()
    );
  }

  @Test
  void 文字列を渡して生成できること() {
    // given
    String id = "23456789";

    // when
    var result = new SongId(id);

    // then
    assertEquals(id, result.value());
  }

  @Test
  void 楽曲IDが生成できること() {
    // when
    var result = SongId.generate();

    // then
    assertNotNull(result.value());
    assertEquals(8, result.value().length());
  }

}
