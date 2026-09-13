package com.inkblogdb.ddd.domain.model.player;

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
      PlayerName playerName = new PlayerName("テストプレイヤー名");

      // when
      PlayerId result = playerService.addPlayer(playerName);

      // then
      verify(playerRepository, times(1)).addPlayer(any());
      assertNotNull(result);
      assertNotNull(result.value());
      assertNotNull(result.value());
    }
  }

}
