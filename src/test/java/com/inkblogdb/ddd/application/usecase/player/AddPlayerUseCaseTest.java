package com.inkblogdb.ddd.application.usecase.player;

import com.inkblogdb.ddd.application.usecase.abort.ExistsPlayerException;
import com.inkblogdb.ddd.domain.model.player.PlayerId;
import com.inkblogdb.ddd.domain.model.player.PlayerName;
import com.inkblogdb.ddd.domain.model.player.PlayerRepository;
import com.inkblogdb.ddd.domain.model.player.PlayerService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
      PlayerId playerId = PlayerId.generate();
      PlayerName playerName = new PlayerName("テストプレイヤー名");
      when(playerRepository.exists(playerId)).thenReturn(false);

      // when
      addPlayerUseCase.addPlayer(playerId, playerName);

      // then
      verify(playerService).addPlayer(playerId, playerName);
    }
  }

}
