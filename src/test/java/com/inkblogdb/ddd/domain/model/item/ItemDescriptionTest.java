package com.inkblogdb.ddd.domain.model.item;

import com.inkblogdb.ddd.domain.DomainException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemDescriptionTest {

  @Test
  void nullを渡すとエラーになること() {
    // when then
    assertEquals(
        "アイテム説明が空",
        assertThrows(DomainException.class, () -> new ItemDescription(null)).getMessage()
    );
  }

  @Test
  void 空白を渡すとエラーになること() {
    // when then
    assertEquals(
        "アイテム説明が空",
        assertThrows(DomainException.class, () -> new ItemDescription("")).getMessage()
    );
    assertEquals(
        "アイテム説明が空",
        assertThrows(DomainException.class, () -> new ItemDescription(" ")).getMessage()
    );
    assertEquals(
        "アイテム説明が空",
        assertThrows(DomainException.class, () -> new ItemDescription("　")).getMessage()
    );
  }

  @Test
  void 値が150桁を超えるとエラーになること() {
    // when then
    assertEquals(
        "アイテム説明が150桁を超えている 1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901",
        assertThrows(DomainException.class, () -> new ItemDescription("1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901")).getMessage()
    );
  }

  @Test
  void 値が150桁だと値が取得できること() {
    //given
    String name = "123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890";

    // when
    var result = new ItemDescription(name);

    // then
    assertEquals(name, result.value());
  }

}
