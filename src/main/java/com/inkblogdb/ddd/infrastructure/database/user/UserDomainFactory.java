package com.inkblogdb.ddd.infrastructure.database.user;

import com.inkblogdb.ddd.domain.model.user.User;
import com.inkblogdb.ddd.domain.model.user.UserId;
import com.inkblogdb.ddd.domain.model.user.UserName;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserDomainFactory {

  public Optional<User> createFrom(UserRecordEntity userRecordEntity) {
    return Optional.ofNullable(userRecordEntity)
        .map(entity -> new User(
                new UserId(entity.id()),
                new UserName(entity.name())
            )
        );
  }

}
