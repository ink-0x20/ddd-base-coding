package com.inkblogdb.ddd.application.usecase.user;

import com.inkblogdb.ddd.application.dto.user.UserDTO;
import com.inkblogdb.ddd.application.usecase.abort.UserNotFondException;
import com.inkblogdb.ddd.domain.model.user.User;
import com.inkblogdb.ddd.domain.model.user.UserId;
import com.inkblogdb.ddd.domain.model.user.UserName;
import com.inkblogdb.ddd.domain.model.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetUserUseCaseTest {

  @InjectMocks
  private GetUserUseCase getUserUseCase;
  @Mock
  private UserRepository userRepository;

  @Test
  void ユーザーを取得できること() throws UserNotFondException {
    // given
    UserId userId = UserId.generate();
    User user = new User(
        userId,
        new UserName("テストユーザー名")
    );
    when(userRepository.findById(userId)).thenReturn(Optional.of(user));

    // when
    UserDTO result = getUserUseCase.getUser(userId);

    // then
    assertEquals(userId.toString(), result.getId());
    assertEquals(user.userName().value(), result.getName());
  }

}
