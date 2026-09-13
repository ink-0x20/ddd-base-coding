package com.inkblogdb.ddd.application.usecase.user;

import com.inkblogdb.ddd.application.command.user.UserCommand;
import com.inkblogdb.ddd.application.usecase.abort.ExistsUserException;
import com.inkblogdb.ddd.domain.model.user.UserId;
import com.inkblogdb.ddd.domain.model.user.UserRepository;
import com.inkblogdb.ddd.domain.model.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AddUserUseCase {

  private final UserRepository userRepository;
  private final UserService userService;

  public void addUser(UserCommand userCommand) throws ExistsUserException {
    UserId userId = new UserId(userCommand.userId());
    if (userRepository.existsByUserId(userId)) {
      throw new ExistsUserException("ユーザーが重複");
    }
    // ユーザーを追加
    userService.addUser(userId);
  }

}
