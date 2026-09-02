package com.inkblogdb.ddd.application.usecase.user;

import com.inkblogdb.ddd.application.dto.user.UserDTO;
import com.inkblogdb.ddd.domain.model.user.UserId;
import com.inkblogdb.ddd.domain.model.user.UserName;
import com.inkblogdb.ddd.domain.model.user.UserService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AddUserUseCaseTest {

  @InjectMocks
  private AddUserUseCase addUserUseCase;
  @Mock
  private UserService userService;

  @Nested
  class addUser {
    @Test
    void ユーザーを追加できること() {
      // given
      UserId userId = UserId.generate();
      UserName userName = new UserName("テストユーザー名");
      when(userService.addUser(userName)).thenReturn(userId);

      // when
      UserDTO result = addUserUseCase.addUser(userName);

      // then
      assertEquals(userId.toString(), result.getId());
      assertEquals(userName.value(), result.getName());
    }
  }

}
