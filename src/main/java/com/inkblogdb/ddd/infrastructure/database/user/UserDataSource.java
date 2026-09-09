package com.inkblogdb.ddd.infrastructure.database.user;

import com.inkblogdb.ddd.domain.model.user.User;
import com.inkblogdb.ddd.domain.model.user.UserId;
import com.inkblogdb.ddd.domain.model.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserDataSource implements UserRepository {

  private final UserMapper userMapper;
  private final UserDomainFactory userDomainFactory;

  public Optional<User> findById(UserId userId) {
    return userDomainFactory.createFrom(userMapper.findById(userId.value()));
  }

  public void save(User user) {
    UserRecordEntity entity = new UserRecordEntity(
        user.userId().value(),
        user.userName().value()
    );
    userMapper.save(entity);
  }

}
