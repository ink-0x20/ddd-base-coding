package com.inkblogdb.ddd.infrastructure.database.command.user;

import com.inkblogdb.ddd.domain.model.user.User;
import com.inkblogdb.ddd.domain.model.user.UserRepository;
import com.inkblogdb.ddd.domain.model.user.UserId;
import com.inkblogdb.ddd.domain.model.player.PlayerId;
import com.inkblogdb.ddd.domain.type.Exists;
import com.inkblogdb.ddd.infrastructure.InfrastructureException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserDataSource implements UserRepository {

  private final UserMapper userMapper;
  private final UserDomainFactory userDomainFactory;

  @Override
  public Exists existsByUserId(UserId userId) {
    return userMapper.existsByUserId(userId.value());
  }

  @Override
  public Exists existsByPlayerId(PlayerId playerId) {
    return userMapper.existsByPlayerId(playerId.value());
  }

  @Override
  public User findById(UserId userId) {
    return userDomainFactory.createFrom(
        userMapper.findById(userId.value())
    );
  }

  @Override
  public void addUser(UserId userId, PlayerId playerId) {
    UserRecordEntity userRecordEntity = new UserRecordEntity(
        userId.value(),
        playerId.value()
    );
    try {
      userMapper.addUser(userRecordEntity);
    } catch (Exception e) {
      throw new InfrastructureException(e);
    }
  }

}
