package com.inkblogdb.ddd.application.usecase.player;

import com.inkblogdb.ddd.application.dto.player.PlayerDTO;
import com.inkblogdb.ddd.application.usecase.abort.PlayerNotFondException;
import com.inkblogdb.ddd.domain.model.player.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetPlayerUseCaseTest {

  @InjectMocks
  private GetPlayerUseCase getPlayerUseCase;
  @Mock
  private PlayerRepository playerRepository;

  @Test
  void プレイヤーを取得できること() throws PlayerNotFondException {
    // given
    PlayerId playerId = PlayerId.generate();
    Player player = new Player(
        playerId,
        new PlayerName("テストプレイヤー名")
    );
    when(playerRepository.findById(playerId)).thenReturn(Optional.of(player));

    // when
    PlayerDTO result = getPlayerUseCase.getPlayer(playerId);

    // then
    assertEquals(playerId.value(), result.id());
    assertEquals(player.playerName().value(), result.name());
  }

}
