package com.inkblogdb.ddd.domain.model.user;

import com.inkblogdb.ddd.domain.DomainException;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

  @InjectMocks
  private UserService userService;
  @Mock
  private UserRepository userRepository;

  @Test
  void addUserが実行されること() {
    // given
    UserId userId = new UserId(UUID.randomUUID().toString());
    when(userRepository.existsByPlayerId(any())).thenReturn(Existence.NO);

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
        .thenReturn(Existence.YES)
        .thenReturn(Existence.YES)
        .thenReturn(Existence.YES)
        .thenReturn(Existence.YES)
        .thenReturn(Existence.YES)
        .thenReturn(Existence.YES)
        .thenReturn(Existence.YES)
        .thenReturn(Existence.YES)
        .thenReturn(Existence.YES)
        .thenReturn(Existence.NO);

    // when
    userService.addUser(userId);

    // then
    verify(userRepository).addUser(any(), any());
    verify(userRepository, times(10)).existsByPlayerId(any());
  }

  @Test
  void 試行回数10回で作成できない場合エラーになること() {
    // given
    UserId userId = new UserId(UUID.randomUUID().toString());
    when(userRepository.existsByPlayerId(any()))
        .thenReturn(Existence.YES)
        .thenReturn(Existence.YES)
        .thenReturn(Existence.YES)
        .thenReturn(Existence.YES)
        .thenReturn(Existence.YES)
        .thenReturn(Existence.YES)
        .thenReturn(Existence.YES)
        .thenReturn(Existence.YES)
        .thenReturn(Existence.YES)
        .thenReturn(Existence.YES);

    // when then
    assertEquals(
        "プレイヤーIDの生成に失敗",
        assertThrows(DomainException.class, () -> userService.addUser(userId)).getMessage()
    );
    verify(userRepository, times(10)).existsByPlayerId(any());
  }

}
