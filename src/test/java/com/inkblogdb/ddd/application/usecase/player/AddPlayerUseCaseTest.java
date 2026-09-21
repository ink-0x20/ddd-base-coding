package com.inkblogdb.ddd.application.usecase.player;

import com.inkblogdb.ddd.application.usecase.abort.ExistsPlayerException;
import com.inkblogdb.ddd.domain.model.player.PlayerId;
import com.inkblogdb.ddd.domain.model.player.PlayerRepository;
import com.inkblogdb.ddd.domain.type.Existence;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AddPlayerUseCaseTest {

  @InjectMocks
  private AddPlayerUseCase addPlayerUseCase;
  @Mock
  private PlayerRepository playerRepository;

  @Test
  void 追加できること() throws ExistsPlayerException {
    // given
    AddPlayerCommand addPlayerCommand = new AddPlayerCommand(
        PlayerId.generate().value(),
        "テストプレイヤー名"
    );
    when(playerRepository.exists(any())).thenReturn(Existence.NO);

    // when
    addPlayerUseCase.addPlayer(addPlayerCommand);

    // then
    verify(playerRepository).addPlayer(any(), any());
  }

  @Test
  void 既に存在する場合エラーになること() {
    // given
    AddPlayerCommand addPlayerCommand = new AddPlayerCommand(
        PlayerId.generate().value(),
        "テストプレイヤー名"
    );
    when(playerRepository.exists(any())).thenReturn(Existence.YES);

    // when then
    assertEquals(
        "プレイヤーが重複",
        assertThrows(ExistsPlayerException.class, () -> addPlayerUseCase.addPlayer(addPlayerCommand)).getMessage()
    );
  }

}
