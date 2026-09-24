package com.inkblogdb.ddd.domain.model.user;

import com.inkblogdb.ddd.domain.DomainException;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class UserIdTest {

  @Test
  void nullを渡すとエラーになること() {
    // when then
    assertEquals(
        "ユーザーIDが空",
        assertThrows(DomainException.class, () -> new UserId((UUID) null)).getMessage()
    );
    assertEquals(
        "ユーザーIDが空",
        assertThrows(DomainException.class, () -> new UserId((String) null)).getMessage()
    );
  }

  @Test
  void 空白を渡すとエラーになること() {
    // when then
    assertEquals(
        "ユーザーIDが空",
        assertThrows(DomainException.class, () -> new UserId("")).getMessage()
    );
    assertEquals(
        "ユーザーIDが空",
        assertThrows(DomainException.class, () -> new UserId(" ")).getMessage()
    );
    assertEquals(
        "ユーザーIDが空",
        assertThrows(DomainException.class, () -> new UserId("　")).getMessage()
    );
  }

  @Test
  void UUIDv4形式でないとエラーになること() {
    // when then
    assertEquals(
        "ユーザーIDの形式が不正 a",
        assertThrows(DomainException.class, () -> new UserId("a")).getMessage()
    );
    assertEquals(
        "ユーザーIDの形式が不正 812be594-7454-4bc3-9038-2e8d8fc0c22xx",
        assertThrows(DomainException.class, () -> new UserId("812be594-7454-4bc3-9038-2e8d8fc0c22xx")).getMessage()
    );
    assertEquals(
        "ユーザーIDの形式が不正 812be594-7454-7bc3-9038-2e8d8fc0c22xx",
        assertThrows(DomainException.class, () -> new UserId("812be594-7454-7bc3-9038-2e8d8fc0c22xx")).getMessage()
    );
  }

  @Test
  void 文字列を渡して生成できること() {
    // given
    String id = UUID.randomUUID().toString();

    // when
    var result = new UserId(id);

    // then
    assertEquals(id, result.toString());
  }

  @Test
  void UUIDを渡して生成できること() {
    // given
    UUID id = UUID.randomUUID();

    // when
    var result = new UserId(id);

    // then
    assertEquals(id, result.value());
  }

}
