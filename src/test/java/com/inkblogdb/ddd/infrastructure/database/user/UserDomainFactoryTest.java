package com.inkblogdb.ddd.infrastructure.database.user;

import com.inkblogdb.ddd.domain.model.user.User;
import com.inkblogdb.ddd.domain.model.user.UserId;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class UserDomainFactoryTest {

  @InjectMocks
  private UserDomainFactory userDomainFactory;

  @Nested
  class createFrom {
    @Test
    void 引数に値がある場合ユーザーが取得できること() {
      // given
      UserId userId = UserId.generate();
      UserRecordEntity entity = new UserRecordEntity(
          userId.value(),
          "テストユーザー名"
      );

      // when
      Optional<User> result = userDomainFactory.createFrom(entity);

      // then
      assertTrue(result.isPresent());
      assertEquals(userId.value(), result.get().userId().value());
      assertEquals("テストユーザー名", result.get().userName().value());
    }

    @Test
    void 引数がnullの場合ユーザーが空になること() {
      // when
      Optional<User> result = userDomainFactory.createFrom(null);

      // then
      assertTrue(result.isEmpty());
    }
  }

}
