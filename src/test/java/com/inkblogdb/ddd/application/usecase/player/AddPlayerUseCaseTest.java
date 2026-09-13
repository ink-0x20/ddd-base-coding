package com.inkblogdb.ddd.application.usecase.player;

import com.inkblogdb.ddd.application.command.player.AddPlayerCommand;
import com.inkblogdb.ddd.application.usecase.abort.ExistsPlayerException;
import com.inkblogdb.ddd.domain.model.player.PlayerId;
import com.inkblogdb.ddd.domain.model.player.PlayerRepository;
import com.inkblogdb.ddd.domain.model.player.PlayerService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AddPlayerUseCaseTest {

  @InjectMocks
  private AddPlayerUseCase addPlayerUseCase;
  @Mock
  private PlayerRepository playerRepository;
  @Mock
  private PlayerService playerService;

  @Nested
  class addPlayer {
    @Test
    void プレイヤーを追加できること() throws ExistsPlayerException {
      // given
      AddPlayerCommand addPlayerCommand = new AddPlayerCommand(
          PlayerId.generate().value(),
          "テストプレイヤー名"
      );
      when(playerRepository.exists(any())).thenReturn(false);

      // when
      addPlayerUseCase.addPlayer(addPlayerCommand);

      // then
      verify(playerService).addPlayer(any(), any());
    }
  }

}
