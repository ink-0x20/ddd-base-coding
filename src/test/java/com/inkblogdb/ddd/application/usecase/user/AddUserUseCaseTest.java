package com.inkblogdb.ddd.application.usecase.user;

import com.inkblogdb.ddd.application.usecase.abort.ExistsUserException;
import com.inkblogdb.ddd.domain.model.user.UserId;
import com.inkblogdb.ddd.domain.model.user.UserRepository;
import com.inkblogdb.ddd.domain.model.user.UserService;
import com.inkblogdb.ddd.domain.type.Exists;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AddUserUseCaseTest {

  @InjectMocks
  private AddUserUseCase addUserUseCase;
  @Mock
  private UserRepository userRepository;
  @Mock
  private UserService userService;

  @Test
  void 追加できること() throws ExistsUserException {
    // given
    UserId userId = new UserId(UUID.randomUUID().toString());
    AddUserCommand addUserCommand = new AddUserCommand(
        userId.toString()
    );
    when(userRepository.existsByUserId(any())).thenReturn(Exists.NOT_EXISTS);

    // when
    addUserUseCase.addUser(addUserCommand);

    // then
    verify(userService).addUser(any());
  }

  @Test
  void 既に存在する場合エラーになること() {
    // given
    UserId userId = new UserId(UUID.randomUUID().toString());
    AddUserCommand addUserCommand = new AddUserCommand(
        userId.toString()
    );
    when(userRepository.existsByUserId(any())).thenReturn(Exists.EXISTS);

    // when then
    assertEquals(
        "ユーザーが重複",
        assertThrows(ExistsUserException.class, () -> addUserUseCase.addUser(addUserCommand)).getMessage()
    );
  }

}
