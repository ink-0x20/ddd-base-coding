package com.inkblogdb.ddd.application.usecase.user;

import com.inkblogdb.ddd.application.usecase.abort.ExistsUserException;
import com.inkblogdb.ddd.domain.model.user.UserId;
import com.inkblogdb.ddd.domain.model.user.UserRepository;
import com.inkblogdb.ddd.domain.model.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class AddUserUseCase {

  private final UserRepository userRepository;
  private final UserService userService;

  @Transactional(isolation = Isolation.READ_COMMITTED)
  public void addUser(AddUserCommand addUserCommand) throws ExistsUserException {
    UserId userId = new UserId(addUserCommand.userId());
    if (userRepository.existsByUserId(userId).yes()) {
      throw new ExistsUserException("ユーザーが重複");
    }
    // ユーザーを追加
    userService.addUser(userId);
  }

}
