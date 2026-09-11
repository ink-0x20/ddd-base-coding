package com.inkblogdb.ddd.domain.model.song;

import com.inkblogdb.ddd.domain.DomainException;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class SongReleaseDateTimeTest {

  @Test
  void nullを渡すと例外になること() {
    // when then
    assertEquals(
        "楽曲公開日時が空",
        assertThrows(DomainException.class, () -> new SongReleaseDateTime(null)).getMessage()
    );
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
