package com.inkblogdb.ddd.domain.model.item;

import com.inkblogdb.ddd.domain.DomainException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemNameTest {

  @Test
  void nullを渡すとエラーになること() {
    // when then
    assertEquals(
        "アイテム名が空",
        assertThrows(DomainException.class, () -> new ItemName(null)).getMessage()
    );
  }

  @Test
  void 空白を渡すとエラーになること() {
    // when then
    assertEquals(
        "アイテム名が空",
        assertThrows(DomainException.class, () -> new ItemName("")).getMessage()
    );
    assertEquals(
        "アイテム名が空",
        assertThrows(DomainException.class, () -> new ItemName(" ")).getMessage()
    );
    assertEquals(
        "アイテム名が空",
        assertThrows(DomainException.class, () -> new ItemName("　")).getMessage()
    );
  }

  @Test
  void 値が50桁を超えるとエラーになること() {
    // when then
    assertEquals(
        "アイテム名が50桁を超えている 123456789012345678901234567890123456789012345678901",
        assertThrows(DomainException.class, () -> new ItemName("123456789012345678901234567890123456789012345678901")).getMessage()
    );
  }

  @Test
  void 値が50桁だと値が取得できること() {
    //given
    String name = "12345678901234567890123456789012345678901234567890";

    // when
    var result = new ItemName(name);

    // then
    assertEquals(name, result.value());
  }

}
