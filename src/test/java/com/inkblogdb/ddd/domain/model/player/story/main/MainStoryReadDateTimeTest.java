package com.inkblogdb.ddd.domain.model.player.story.main;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class MainStoryReadDateTimeTest {

  @Test
  void nullを渡して生成できること() {
    // when
    var result = new MainStoryReadDateTime(null);

    // then
    assertNull(result.value());
    assertFalse(result.isRead());
    assertTrue(result.isUnread());
  }

  @Test
  void 未読状態生成できること() {
    // when
    var result = MainStoryReadDateTime.createUnread();

    // then
    assertNull(result.value());
    assertFalse(result.isRead());
    assertTrue(result.isUnread());
  }

  @Test
  void 日時を渡して生成できること() {
    // given
    var value = LocalDateTime.now();

    // when
    var result = new MainStoryReadDateTime(value);

    // then
    assertEquals(value, result.value());
    assertTrue(result.isRead());
    assertFalse(result.isUnread());
  }

}
