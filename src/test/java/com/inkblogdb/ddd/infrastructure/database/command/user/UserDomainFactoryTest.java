package com.inkblogdb.ddd.infrastructure.database.command.user;

import com.inkblogdb.ddd.domain.model.player.PlayerId;
import com.inkblogdb.ddd.domain.model.user.User;
import com.inkblogdb.ddd.domain.model.user.UserId;
import com.inkblogdb.ddd.infrastructure.InfrastructureException;
import com.inkblogdb.ddd.infrastructure.database.command.user.UserDomainFactory;
import com.inkblogdb.ddd.infrastructure.database.command.user.UserRecordEntity;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserDomainFactoryTest {

  @InjectMocks
  private UserDomainFactory userDomainFactory;

  @Nested
  class createFrom {
    @Test
    void 生成できること() {
      // given
      UserId userId = new UserId(UUID.randomUUID().toString());
      PlayerId playerId = PlayerId.generate();
      UserRecordEntity entity = new UserRecordEntity(
          userId.value(),
          playerId.value()
      );

      // when
      User result = userDomainFactory.createFrom(entity);

      // then
      assertNotNull(result);
      assertEquals(userId.value(), result.userId().value());
      assertEquals(playerId.value(), result.playerId().value());
    }

    @Test
    void nullの場合エラーになること() {
      // when then
      assertThrows(InfrastructureException.class, () -> userDomainFactory.createFrom(null));
    }
  }

}
