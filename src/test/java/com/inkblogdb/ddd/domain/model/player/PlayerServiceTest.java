package com.inkblogdb.ddd.domain.model.player;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PlayerServiceTest {

  @InjectMocks
  private PlayerService playerService;
  @Mock
  private PlayerRepository playerRepository;

  @Nested
  class addPlayer {
    @Test
    void プレイヤーを追加できること() {
      // given
      PlayerId playerId = PlayerId.generate();
      PlayerName playerName = new PlayerName("テストプレイヤー名");

      // when
      playerService.addPlayer(playerId, playerName);

      // then
      verify(playerRepository).addPlayer(playerId, playerName);
    }
  }

}
