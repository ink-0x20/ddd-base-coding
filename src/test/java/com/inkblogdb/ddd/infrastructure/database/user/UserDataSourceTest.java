package com.inkblogdb.ddd.infrastructure.database.user;

import com.inkblogdb.ddd.domain.model.user.User;
import com.inkblogdb.ddd.domain.model.user.UserId;
import com.inkblogdb.ddd.domain.model.user.UserName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserDataSourceTest {

  @InjectMocks
  private UserDataSource userDataSource;
  @Mock
  private UserMapper userMapper;
  @Mock
  private UserDomainFactory userDomainFactory;

  @Nested
  class findById {
    @Test
    void DBに値がある場合ユーザーが取得できること() {
      // given
      UserId userId = UserId.generate();
      User user = new User(
          userId,
          new UserName("テストユーザー名")
      );
      when(userDomainFactory.createFrom(any())).thenReturn(Optional.of(user));

      // when
      Optional<User> result = userDataSource.findById(userId);

      // then
      assertTrue(result.isPresent());
      assertEquals(userId, result.get().userId());
      assertEquals("テストユーザー名", result.get().userName().value());
    }

    @Test
    void DBに値がない場合ユーザーが空になること() {
      // given
      UserId userId = UserId.generate();
      when(userMapper.findById(any())).thenReturn(null);

      // when
      Optional<User> result = userDataSource.findById(userId);

      // then
      assertTrue(result.isEmpty());
    }
  }

  @Nested
  class save {
    @Test
    void ユーザーを保存できること() {
      // given
      User user = new User(
          UserId.generate(),
          new UserName("テストユーザー名")
      );

      // when then
      assertDoesNotThrow(() -> userDataSource.save(user));
    }
  }

}
