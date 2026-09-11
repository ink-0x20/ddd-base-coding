package com.inkblogdb.ddd.domain.model.song;

import com.inkblogdb.ddd.domain.DomainException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SongNameTest {

  @Test
  void nullを渡すと例外になること() {
    // when then
    assertEquals(
        "楽曲名が空",
        assertThrows(DomainException.class, () -> new SongName(null)).getMessage()
    );
  }

  @Test
  void 空白を渡すと例外になること() {
    // when then
    assertEquals(
        "楽曲名が空",
        assertThrows(DomainException.class, () -> new SongName("")).getMessage()
    );
    assertEquals(
        "楽曲名が空",
        assertThrows(DomainException.class, () -> new SongName(" ")).getMessage()
    );
    assertEquals(
        "楽曲名が空",
        assertThrows(DomainException.class, () -> new SongName("　")).getMessage()
    );
  }

  @Test
  void 値が50桁を超えると例外になること() {
    // when then
    assertEquals(
        "楽曲名が50桁を超えている 123456789012345678901234567890123456789012345678901",
        assertThrows(DomainException.class, () -> new SongName("123456789012345678901234567890123456789012345678901")).getMessage()
    );
  }

  @Test
  void 文字列を渡して生成できること() {
    // given
    String name = "12345678901234567890123456789012345678901234567890";

    // when
    var result = new SongName(name);

    // then
    assertEquals(name, result.value());
  }

}
