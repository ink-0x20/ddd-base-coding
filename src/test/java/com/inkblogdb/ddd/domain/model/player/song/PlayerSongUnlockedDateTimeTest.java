package com.inkblogdb.ddd.domain.model.player.song;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class PlayerSongUnlockedDateTimeTest {

  @Test
  void nullを渡して生成できること() {
    // when
    var result = new PlayerSongUnlockedDateTime(null);

    // then
    assertNull(result.value());
  }

  @Test
  void 日時を渡して生成できること() {
    // given
    var value = LocalDateTime.now();

    // when
    var result = new PlayerSongUnlockedDateTime(value);

    // then
    assertEquals(value, result.value());
  }

}
