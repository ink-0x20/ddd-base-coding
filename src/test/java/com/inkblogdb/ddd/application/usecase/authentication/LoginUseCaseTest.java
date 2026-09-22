package com.inkblogdb.ddd.application.usecase.authentication;

import com.inkblogdb.ddd.application.usecase.abort.not_found.UserNotFoundException;
import com.inkblogdb.ddd.application.usecase.user.AddUserCommand;
import com.inkblogdb.ddd.domain.model.authentication.Authentication;
import com.inkblogdb.ddd.domain.model.authentication.AuthenticationService;
import com.inkblogdb.ddd.domain.model.authentication.AuthenticationToken;
import com.inkblogdb.ddd.domain.model.player.PlayerId;
import com.inkblogdb.ddd.domain.model.user.User;
import com.inkblogdb.ddd.domain.model.user.UserId;
import com.inkblogdb.ddd.domain.model.user.UserRepository;
import com.inkblogdb.ddd.domain.type.Existence;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LoginUseCaseTest {

  @InjectMocks
  private LoginUseCase loginUseCase;
  @Mock
  private UserRepository userRepository;
  @Mock
  private AuthenticationService authenticationService;

  @Test
  void ログインできること() throws UserNotFoundException {
    // given
    UserId userId = new UserId(UUID.randomUUID().toString());
    AddUserCommand addUserCommand = new AddUserCommand(
        userId.toString()
    );
    PlayerId playerId = PlayerId.generate();
    User user = new User(
        userId,
        playerId
    );
    Authentication authentication = new Authentication(
        playerId,
        new AuthenticationToken("dummy_token")
    );
    when(userRepository.existsByUserId(any())).thenReturn(Existence.YES);
    when(userRepository.findById(any())).thenReturn(user);
    when(authenticationService.login(any())).thenReturn(authentication);

    // when
    LoginModel result = loginUseCase.login(addUserCommand);

    // then
    assertEquals(playerId.value(), result.playerId());
    assertEquals(authentication.authenticationToken().value(), result.token());
  }

  @Test
  void 存在しない場合エラーになること() {
    // given
    UserId userId = new UserId(UUID.randomUUID().toString());
    AddUserCommand addUserCommand = new AddUserCommand(
        userId.toString()
    );
    when(userRepository.existsByUserId(any())).thenReturn(Existence.NO);

    // when then
    assertEquals(
        "ユーザーが存在しない",
        assertThrows(UserNotFoundException.class, () -> loginUseCase.login(addUserCommand)).getMessage()
    );
  }

}
