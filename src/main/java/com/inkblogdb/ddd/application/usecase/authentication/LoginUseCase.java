package com.inkblogdb.ddd.application.usecase.authentication;

import com.inkblogdb.ddd.application.command.user.UserCommand;
import com.inkblogdb.ddd.application.dto.authentication.AuthenticationDTO;
import com.inkblogdb.ddd.application.usecase.abort.UserNotFoundException;
import com.inkblogdb.ddd.domain.model.authentication.Authentication;
import com.inkblogdb.ddd.domain.model.authentication.AuthenticationService;
import com.inkblogdb.ddd.domain.model.user.User;
import com.inkblogdb.ddd.domain.model.user.UserId;
import com.inkblogdb.ddd.domain.model.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LoginUseCase {

  private final UserRepository userRepository;
  private final AuthenticationService authenticationService;

  public AuthenticationDTO login(UserCommand userCommand) throws UserNotFoundException {
    UserId userId = new UserId(userCommand.userId());
    if (userRepository.notExistsByUserId(userId)) {
      throw new UserNotFoundException("ユーザーが存在しない");
    }
    // ユーザーを検索
    User user = userRepository.findById(userId);

    // ログイン
    Authentication authentication = authenticationService.login(user.playerId());
    return new AuthenticationDTO(
        authentication.playerId().value(),
        authentication.authenticationToken().value()
    );
  }

}
