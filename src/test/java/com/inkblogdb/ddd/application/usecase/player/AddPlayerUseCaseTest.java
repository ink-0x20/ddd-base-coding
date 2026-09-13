package com.inkblogdb.ddd.application.usecase.player;

import com.inkblogdb.ddd.application.dto.player.PlayerDTO;
import com.inkblogdb.ddd.domain.model.player.PlayerId;
import com.inkblogdb.ddd.domain.model.player.PlayerName;
import com.inkblogdb.ddd.domain.model.player.PlayerService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AddPlayerUseCaseTest {

  @InjectMocks
  private AddPlayerUseCase addPlayerUseCase;
  @Mock
  private PlayerService playerService;

  @Nested
  class addPlayer {
    @Test
    void プレイヤーを追加できること() {
      // given
      PlayerId playerId = PlayerId.generate();
      PlayerName playerName = new PlayerName("テストプレイヤー名");
      when(playerService.addPlayer(playerName)).thenReturn(playerId);

      // when
      PlayerDTO result = addPlayerUseCase.addPlayer(playerName);

      // then
      assertEquals(playerId.value(), result.id());
      assertEquals(playerName.value(), result.name());
    }
  }

}
