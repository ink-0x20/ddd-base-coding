package com.inkblogdb.ddd.domain.model.song;

import com.inkblogdb.ddd.domain.DomainException;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class SongReleaseDateTimeTest {

  @Test
  void nullを渡して生成できること() {
    // when
    var result = new SongReleaseDateTime(null);

    // then
    assertNull(result.value());
  }

  @Test
  void 日時を渡して生成できること() {
    // given
    var value = LocalDateTime.now();

    // when
    var result = new SongReleaseDateTime(value);

    // then
    assertEquals(value, result.value());
  }

}
