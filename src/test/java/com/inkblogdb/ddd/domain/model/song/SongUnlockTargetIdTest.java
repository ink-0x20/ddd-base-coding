package com.inkblogdb.ddd.domain.model.song;

import com.inkblogdb.ddd.domain.DomainException;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class SongUnlockTargetIdTest {

  @Test
  void nullを渡すと生成できること() {
    // given
    String id = null;

    // when
    var result = new SongUnlockTargetId(id);

    // then
    assertNull(result.value());
  }

  @Test
  void 空白を渡すと生成できること() {
    // when then
    assertEquals(
        "楽曲解放対象IDの形式が不正 ",
        assertThrows(DomainException.class, () -> new SongUnlockTargetId("")).getMessage()
    );
    assertEquals(
        "楽曲解放対象IDの形式が不正  ",
        assertThrows(DomainException.class, () -> new SongUnlockTargetId(" ")).getMessage()
    );
    assertEquals(
        "楽曲解放対象IDの形式が不正 　",
        assertThrows(DomainException.class, () -> new SongUnlockTargetId("　")).getMessage()
    );
  }

  @Test
  void UUIDv4形式でないとエラーになること() {
    // when then
    assertEquals(
        "楽曲解放対象IDの形式が不正 a",
        assertThrows(DomainException.class, () -> new SongUnlockTargetId("a")).getMessage()
    );
    assertEquals(
        "楽曲解放対象IDの形式が不正 812be594-7454-4bc3-9038-2e8d8fc0c22xx",
        assertThrows(DomainException.class, () -> new SongUnlockTargetId("812be594-7454-4bc3-9038-2e8d8fc0c22xx")).getMessage()
    );
    assertEquals(
        "楽曲解放対象IDの形式が不正 812be594-7454-7bc3-9038-2e8d8fc0c22xx",
        assertThrows(DomainException.class, () -> new SongUnlockTargetId("812be594-7454-7bc3-9038-2e8d8fc0c22xx")).getMessage()
    );
  }

  @Test
  void 文字列を渡して生成できること() {
    // given
    String id = UUID.randomUUID().toString();

    // when
    var result = new SongUnlockTargetId(id);

    // then
    assertEquals(id, result.toString());
  }

  @Test
  void UUIDを渡して生成できること() {
    // given
    UUID id = UUID.randomUUID();

    // when
    var result = new SongUnlockTargetId(id);

    // then
    assertEquals(id, result.value());
  }

}
