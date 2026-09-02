package com.inkblogdb.ddd.domain.model.user;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

  @InjectMocks
  private UserService userService;
  @Mock
  private UserRepository userRepository;

  @Nested
  class addUser {
    @Test
    void ユーザーを追加できること() {
      // given
      UserName userName = new UserName("テストユーザー名");

      // when
      UserId result = userService.addUser(userName);

      // then
      verify(userRepository, times(1)).save(any());
      assertNotNull(result);
      assertNotNull(result.value());
      assertNotNull(result.toString());
    }
  }

}
