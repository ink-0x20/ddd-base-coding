package com.inkblogdb.ddd.infrastructure.database.command.user;

import com.inkblogdb.ddd.domain.model.player.PlayerId;
import com.inkblogdb.ddd.domain.model.user.User;
import com.inkblogdb.ddd.domain.model.user.UserId;
import com.inkblogdb.ddd.infrastructure.InfrastructureException;
import org.springframework.stereotype.Component;

@Component
public class UserDomainFactory {

  public User createFrom(UserRecordEntity userRecordEntity) {
    if (userRecordEntity == null) {
      throw new InfrastructureException("ユーザーレコードが存在しない");
    }
    return new User(
        new UserId(userRecordEntity.id()),
        new PlayerId(userRecordEntity.playerId())
    );
  }

}
