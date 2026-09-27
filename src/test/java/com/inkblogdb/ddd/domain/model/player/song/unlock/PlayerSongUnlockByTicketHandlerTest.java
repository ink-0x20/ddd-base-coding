package com.inkblogdb.ddd.domain.model.player.song.unlock;

import com.inkblogdb.ddd.domain.model.item.ItemId;
import com.inkblogdb.ddd.domain.model.player.PlayerId;
import com.inkblogdb.ddd.domain.model.player.item.PlayerItem;
import com.inkblogdb.ddd.domain.model.player.item.PlayerItemQuantity;
import com.inkblogdb.ddd.domain.model.player.item.PlayerItemRepository;
import com.inkblogdb.ddd.domain.model.song.*;
import com.inkblogdb.ddd.domain.type.Unlockability;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PlayerSongUnlockByTicketHandlerTest {

  @InjectMocks
  private PlayerSongUnlockByTicketHandler playerSongUnlockByTicketHandler;
  @Mock
  private SongRepository songRepository;
  @Mock
  private PlayerItemRepository playerItemRepository;
  @Mock
  private Song song;

  @Nested
  class canUnlock {
    @Test
    void 所持数が足りている場合アンロックできると判定されること() {
      // given
      PlayerId playerId = PlayerId.generate();
      SongId songId = new SongId("zzzzzzzz");
      SongUnlockTargetId songUnlockTargetId = new SongUnlockTargetId(UUID.randomUUID());
      SongUnlockQuantity songUnlockQuantity = new SongUnlockQuantity(1);
      PlayerItem playerItem = new PlayerItem(
          playerId,
          new ItemId(songUnlockTargetId.value()),
          new PlayerItemQuantity(100)
      );
      when(songRepository.findById(any())).thenReturn(song);
      when(song.songUnlockTargetId()).thenReturn(songUnlockTargetId);
      when(song.songUnlockQuantity()).thenReturn(songUnlockQuantity);
      when(playerItemRepository.findById(any(), any())).thenReturn(Optional.of(playerItem));

      // when
      var result = playerSongUnlockByTicketHandler.canUnlock(playerId, songId);

      // then
      assertEquals(Unlockability.UNLOCKABLE, result);
    }

    @Test
    void 所持数が足りていない場合アンロックできないと判定されること() {
      // given
      PlayerId playerId = PlayerId.generate();
      SongId songId = new SongId("zzzzzzzz");
      SongUnlockTargetId songUnlockTargetId = new SongUnlockTargetId(UUID.randomUUID());
      SongUnlockQuantity songUnlockQuantity = new SongUnlockQuantity(2);
      PlayerItem playerItem = new PlayerItem(
          playerId,
          new ItemId(songUnlockTargetId.value()),
          new PlayerItemQuantity(1)
      );
      when(songRepository.findById(any())).thenReturn(song);
      when(song.songUnlockTargetId()).thenReturn(songUnlockTargetId);
      when(song.songUnlockQuantity()).thenReturn(songUnlockQuantity);
      when(playerItemRepository.findById(any(), any())).thenReturn(Optional.of(playerItem));

      // when
      var result = playerSongUnlockByTicketHandler.canUnlock(playerId, songId);

      // then
      assertEquals(Unlockability.LOCKED, result);
    }

    @Test
    void 所持数が0の場合アンロックできないと判定されること() {
      // given
      PlayerId playerId = PlayerId.generate();
      SongId songId = new SongId("zzzzzzzz");
      SongUnlockTargetId songUnlockTargetId = new SongUnlockTargetId(UUID.randomUUID());
      SongUnlockQuantity songUnlockQuantity = new SongUnlockQuantity(1);
      when(songRepository.findById(any())).thenReturn(song);
      when(song.songUnlockTargetId()).thenReturn(songUnlockTargetId);
      when(song.songUnlockQuantity()).thenReturn(songUnlockQuantity);
      when(playerItemRepository.findById(any(), any())).thenReturn(Optional.empty());

      // when
      var result = playerSongUnlockByTicketHandler.canUnlock(playerId, songId);

      // then
      assertEquals(Unlockability.LOCKED, result);
    }
  }

  @Nested
  class executeUnlockAction {
    @Test
    void 所持数を更新できること() {
      // given
      PlayerId playerId = PlayerId.generate();
      SongId songId = new SongId("zzzzzzzz");
      SongUnlockTargetId songUnlockTargetId = new SongUnlockTargetId(UUID.randomUUID());
      SongUnlockQuantity songUnlockQuantity = new SongUnlockQuantity(1);
      PlayerItem playerItem = new PlayerItem(
          playerId,
          new ItemId(songUnlockTargetId.value()),
          new PlayerItemQuantity(100)
      );
      when(songRepository.findById(any())).thenReturn(song);
      when(song.songUnlockTargetId()).thenReturn(songUnlockTargetId);
      when(song.songUnlockQuantity()).thenReturn(songUnlockQuantity);
      when(playerItemRepository.findById(any(), any())).thenReturn(Optional.of(playerItem));

      // when
      playerSongUnlockByTicketHandler.executeUnlockAction(playerId, songId);

      // then
      verify(playerItemRepository).updateQuantity(any());
    }
  }

}
