package com.inkblogdb.ddd.infrastructure.database.user;

import com.inkblogdb.ddd.domain.model.user.User;
import com.inkblogdb.ddd.domain.model.user.UserRepository;
import com.inkblogdb.ddd.domain.model.user.UserId;
import com.inkblogdb.ddd.domain.model.player.PlayerId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserDataSource implements UserRepository {

  private final UserMapper userMapper;

  @Override
  public boolean existsByUserId(UserId userId) {
    return userMapper.existsByUserId(userId.value());
  }

  @Override
  public boolean notExistsByUserId(UserId userId) {
    return !existsByUserId(userId);
  }

  @Override
  public boolean notExistsByPlayerId(PlayerId playerId) {
    return !userMapper.existsByPlayerId(playerId.value());
  }

  @Override
  public User findById(UserId userId) {
    UserRecordEntity userRecordEntity = userMapper.findById(userId.value());
    return new User(
        new UserId(userRecordEntity.id()),
        new PlayerId(userRecordEntity.playerId())
    );
  }

  @Override
  public void addUser(UserId userId, PlayerId playerId) {
    UserRecordEntity userRecordEntity = new UserRecordEntity(
        userId.value(),
        playerId.value()
    );
    userMapper.addUser(userRecordEntity);
  }

}
