package com.inkblogdb.ddd.domain.model.story;

import com.inkblogdb.ddd.domain.DomainException;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StoryIdTest {

  @Test
  void nullを渡すとエラーになること() {
    // when then
    assertEquals(
        "ストーリーIDが空",
        assertThrows(DomainException.class, () -> new StoryId((UUID) null)).getMessage()
    );
    assertEquals(
        "ストーリーIDが空",
        assertThrows(DomainException.class, () -> new StoryId((String) null)).getMessage()
    );
  }

  @Test
  void 空白を渡すとエラーになること() {
    // when then
    assertEquals(
        "ストーリーIDが空",
        assertThrows(DomainException.class, () -> new StoryId("")).getMessage()
    );
    assertEquals(
        "ストーリーIDが空",
        assertThrows(DomainException.class, () -> new StoryId(" ")).getMessage()
    );
    assertEquals(
        "ストーリーIDが空",
        assertThrows(DomainException.class, () -> new StoryId("　")).getMessage()
    );
  }

  @Test
  void UUIDv4形式でないとエラーになること() {
    // when then
    assertEquals(
        "ストーリーIDの形式が不正 a",
        assertThrows(DomainException.class, () -> new StoryId("a")).getMessage()
    );
    assertEquals(
        "ストーリーIDの形式が不正 812be594-7454-4bc3-9038-2e8d8fc0c22xx",
        assertThrows(DomainException.class, () -> new StoryId("812be594-7454-4bc3-9038-2e8d8fc0c22xx")).getMessage()
    );
    assertEquals(
        "ストーリーIDの形式が不正 812be594-7454-7bc3-9038-2e8d8fc0c22xx",
        assertThrows(DomainException.class, () -> new StoryId("812be594-7454-7bc3-9038-2e8d8fc0c22xx")).getMessage()
    );
  }

  @Test
  void 文字列を渡して生成できること() {
    // given
    String id = UUID.randomUUID().toString();

    // when
    var result = new StoryId(id);

    // then
    assertEquals(id, result.toString());
  }

  @Test
  void UUIDを渡して生成できること() {
    // given
    UUID id = UUID.randomUUID();

    // when
    var result = new StoryId(id);

    // then
    assertEquals(id, result.value());
  }

}
