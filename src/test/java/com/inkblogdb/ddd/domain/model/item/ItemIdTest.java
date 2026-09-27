package com.inkblogdb.ddd.domain.model.item;

import com.inkblogdb.ddd.domain.DomainException;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ItemIdTest {

  @Test
  void nullを渡すとエラーになること() {
    // when then
    assertEquals(
        "アイテムIDが空",
        assertThrows(DomainException.class, () -> new ItemId((UUID) null)).getMessage()
    );
    assertEquals(
        "アイテムIDが空",
        assertThrows(DomainException.class, () -> new ItemId((String) null)).getMessage()
    );
  }

  @Test
  void 空白を渡すとエラーになること() {
    // when then
    assertEquals(
        "アイテムIDが空",
        assertThrows(DomainException.class, () -> new ItemId("")).getMessage()
    );
    assertEquals(
        "アイテムIDが空",
        assertThrows(DomainException.class, () -> new ItemId(" ")).getMessage()
    );
    assertEquals(
        "アイテムIDが空",
        assertThrows(DomainException.class, () -> new ItemId("　")).getMessage()
    );
  }

  @Test
  void UUIDv4形式でないとエラーになること() {
    // when then
    assertEquals(
        "アイテムIDの形式が不正 a",
        assertThrows(DomainException.class, () -> new ItemId("a")).getMessage()
    );
    assertEquals(
        "アイテムIDの形式が不正 812be594-7454-4bc3-9038-2e8d8fc0c22xx",
        assertThrows(DomainException.class, () -> new ItemId("812be594-7454-4bc3-9038-2e8d8fc0c22xx")).getMessage()
    );
    assertEquals(
        "アイテムIDの形式が不正 812be594-7454-7bc3-9038-2e8d8fc0c22xx",
        assertThrows(DomainException.class, () -> new ItemId("812be594-7454-7bc3-9038-2e8d8fc0c22xx")).getMessage()
    );
  }

  @Test
  void 文字列を渡して生成できること() {
    // given
    String id = UUID.randomUUID().toString();

    // when
    var result = new ItemId(id);

    // then
    assertEquals(id, result.toString());
  }

  @Test
  void UUIDを渡して生成できること() {
    // given
    UUID id = UUID.randomUUID();

    // when
    var result = new ItemId(id);

    // then
    assertEquals(id, result.value());
  }

}
