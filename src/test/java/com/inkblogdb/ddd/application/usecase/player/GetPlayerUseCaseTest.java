package com.inkblogdb.ddd.application.usecase.player;

import com.inkblogdb.ddd.application.usecase.abort.PlayerNotFoundException;
import com.inkblogdb.ddd.domain.model.player.Player;
import com.inkblogdb.ddd.domain.model.player.PlayerId;
import com.inkblogdb.ddd.domain.model.player.PlayerName;
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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetPlayerUseCaseTest {

  @InjectMocks
  private GetPlayerUseCase getPlayerUseCase;
  @Mock
  private PlayerRepository playerRepository;

  @Test
  void プレイヤーを取得できること() throws PlayerNotFoundException {
    // given
    PlayerId playerId = PlayerId.generate();
    GetPlayerCommand getPlayerCommand = new GetPlayerCommand(
        playerId.value()
    );
    Player player = new Player(
        playerId,
        new PlayerName("テストプレイヤー名")
    );
    when(playerRepository.exists(any())).thenReturn(Existence.YES);
    when(playerRepository.findById(any())).thenReturn(player);

    // when
    GetPlayerModel result = getPlayerUseCase.getPlayer(getPlayerCommand);

    // then
    assertEquals(playerId.value(), result.id());
    assertEquals(player.playerName().value(), result.name());
  }

  @Test
  void 存在しない場合エラーになること() {
    // given
    PlayerId playerId = PlayerId.generate();
    GetPlayerCommand getPlayerCommand = new GetPlayerCommand(
        playerId.value()
    );
    when(playerRepository.exists(any())).thenReturn(Existence.NO);

    // when then
    assertEquals(
        "プレイヤーが存在しない",
        assertThrows(PlayerNotFoundException.class, () -> getPlayerUseCase.getPlayer(getPlayerCommand)).getMessage()
    );
  }

}
