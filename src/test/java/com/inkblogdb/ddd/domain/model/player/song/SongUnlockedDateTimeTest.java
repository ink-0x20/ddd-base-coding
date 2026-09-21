package com.inkblogdb.ddd.domain.model.player.song;

import com.inkblogdb.ddd.domain.DomainException;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SongUnlockedDateTimeTest {

  @Test
  void nullを渡すとエラーになること() {
    // when then
    assertEquals(
        "楽曲解放日時が空",
        assertThrows(DomainException.class, () -> new SongUnlockedDateTime(null)).getMessage()
    );
  }

  @Test
  void 日時を渡して生成できること() {
    // given
    var value = LocalDateTime.now();

    // when
    var result = new SongUnlockedDateTime(value);

    // then
    assertEquals(value, result.value());
  }

}
