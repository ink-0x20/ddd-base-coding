package com.inkblogdb.ddd.infrastructure.database.user;

import com.inkblogdb.ddd.domain.model.player.PlayerId;
import com.inkblogdb.ddd.domain.model.user.User;
import com.inkblogdb.ddd.domain.model.user.UserId;
import com.inkblogdb.ddd.domain.type.Exists;
import com.inkblogdb.ddd.infrastructure.InfrastructureException;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserDataSourceTest {

  @InjectMocks
  private UserDataSource userDataSource;
  @Mock
  private UserMapper userMapper;
  @Mock
  private UserDomainFactory userDomainFactory;

  @Nested
  class existsByUserId {
    @Test
    void 存在すること() {
      // given
      when(userMapper.existsByUserId(any())).thenReturn(Exists.EXISTS);

      // when
      Exists result = userDataSource.existsByUserId(new UserId(UUID.randomUUID().toString()));

      // then
      assertTrue(result.isExists());
    }

    @Test
    void 存在しないこと() {
      // given
      when(userMapper.existsByUserId(any())).thenReturn(Exists.NOT_EXISTS);

      // when
      Exists result = userDataSource.existsByUserId(new UserId(UUID.randomUUID().toString()));

      // then
      assertTrue(result.isNotExists());
    }
  }

  @Nested
  class existsByPlayerId {
    @Test
    void 存在すること() {
      // given
      when(userMapper.existsByPlayerId(any())).thenReturn(Exists.EXISTS);

      // when
      Exists result = userDataSource.existsByPlayerId(PlayerId.generate());

      // then
      assertTrue(result.isExists());
    }

    @Test
    void 存在しないこと() {
      // given
      when(userMapper.existsByPlayerId(any())).thenReturn(Exists.NOT_EXISTS);

      // when
      Exists result = userDataSource.existsByPlayerId(PlayerId.generate());

      // then
      assertTrue(result.isNotExists());
    }
  }

  @Nested
  class findById {
    @Test
    void 値が取得できること() {
      // given
      UserId userId = new UserId(UUID.randomUUID().toString());
      PlayerId playerId = PlayerId.generate();
      User user = new User(
          userId,
          playerId
      );
      when(userDomainFactory.createFrom(any())).thenReturn(user);

      // when
      User result = userDataSource.findById(userId);

      // then
      assertNotNull(result);
      assertEquals(userId.value(), result.userId().value());
      assertEquals(playerId.value(), result.playerId().value());
    }

    @Test
    void 存在しない場合エラーになること() {
      // given
      UserId userId = new UserId(UUID.randomUUID().toString());
      when(userDomainFactory.createFrom(any())).thenThrow(InfrastructureException.class);

      // when then
      assertThrows(InfrastructureException.class, () -> userDataSource.findById(userId));
    }
  }

  @Nested
  class addUser {
    @Test
    void 値が保存できること() {
      // given
      UserId userId = new UserId(UUID.randomUUID().toString());
      PlayerId playerId = PlayerId.generate();

      // when
      userDataSource.addUser(userId, playerId);

      // then
      verify(userMapper).addUser(any());
    }

    @Test
    void 失敗した場合エラーになること() {
      // given
      UserId userId = new UserId(UUID.randomUUID().toString());
      PlayerId playerId = PlayerId.generate();
      doThrow(InfrastructureException.class).when(userMapper).addUser(any());

      // when then
      assertThrows(InfrastructureException.class, () -> userDataSource.addUser(userId, playerId));
    }
  }

}
