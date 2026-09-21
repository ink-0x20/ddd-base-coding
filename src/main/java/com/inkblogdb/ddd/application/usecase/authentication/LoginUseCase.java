package com.inkblogdb.ddd.application.usecase.authentication;

import com.inkblogdb.ddd.application.usecase.user.AddUserCommand;
import com.inkblogdb.ddd.application.usecase.abort.UserNotFoundException;
import com.inkblogdb.ddd.domain.model.authentication.Authentication;
import com.inkblogdb.ddd.domain.model.authentication.AuthenticationService;
import com.inkblogdb.ddd.domain.model.user.User;
import com.inkblogdb.ddd.domain.model.user.UserId;
import com.inkblogdb.ddd.domain.model.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class LoginUseCase {

  private final UserRepository userRepository;
  private final AuthenticationService authenticationService;

  @Transactional(isolation = Isolation.READ_COMMITTED)
  public LoginModel login(AddUserCommand addUserCommand) throws UserNotFoundException {
    UserId userId = new UserId(addUserCommand.userId());
    if (userRepository.existsByUserId(userId).no()) {
      throw new UserNotFoundException("ユーザーが存在しない");
    }
    // ユーザーを検索
    User user = userRepository.findById(userId);

    // ログイン
    Authentication authentication = authenticationService.login(user.playerId());
    return new LoginModel(
        authentication.playerId().value(),
        authentication.authenticationToken().value()
    );
  }

}
