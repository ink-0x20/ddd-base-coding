package com.inkblogdb.ddd.domain.model.user;

import com.inkblogdb.ddd.domain.DomainException;
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
class UserServiceTest {

  @InjectMocks
  private UserService userService;
  @Mock
  private UserRepository userRepository;

  @Test
  void 追加できること() {
    // given
    UserId userId = new UserId(UUID.randomUUID().toString());
    when(userRepository.existsByPlayerId(any())).thenReturn(Exists.NOT_EXISTS);

    // when
    userService.addUser(userId);

    // then
    verify(userRepository).addUser(any(), any());
  }

  @Test
  void 試行回数10回目で追加できること() {
    // given
    UserId userId = new UserId(UUID.randomUUID().toString());
    when(userRepository.existsByPlayerId(any()))
        .thenReturn(Exists.EXISTS)
        .thenReturn(Exists.EXISTS)
        .thenReturn(Exists.EXISTS)
        .thenReturn(Exists.EXISTS)
        .thenReturn(Exists.EXISTS)
        .thenReturn(Exists.EXISTS)
        .thenReturn(Exists.EXISTS)
        .thenReturn(Exists.EXISTS)
        .thenReturn(Exists.EXISTS)
        .thenReturn(Exists.NOT_EXISTS);

    // when
    userService.addUser(userId);

    // then
    verify(userRepository).addUser(any(), any());
  }

  @Test
  void 試行回数11回目でエラーになること() {
    // given
    UserId userId = new UserId(UUID.randomUUID().toString());
    when(userRepository.existsByPlayerId(any()))
        .thenReturn(Exists.EXISTS)
        .thenReturn(Exists.EXISTS)
        .thenReturn(Exists.EXISTS)
        .thenReturn(Exists.EXISTS)
        .thenReturn(Exists.EXISTS)
        .thenReturn(Exists.EXISTS)
        .thenReturn(Exists.EXISTS)
        .thenReturn(Exists.EXISTS)
        .thenReturn(Exists.EXISTS)
        .thenReturn(Exists.EXISTS)
        .thenReturn(Exists.NOT_EXISTS);

    // when then
    assertEquals(
        "プレイヤーIDの生成に失敗",
        assertThrows(DomainException.class, () -> userService.addUser(userId)).getMessage()
    );
  }

}
