package com.inkblogdb.ddd.domain.model.player.song.unlock;

import com.inkblogdb.ddd.domain.model.item.ItemId;
import com.inkblogdb.ddd.domain.model.player.PlayerId;
import com.inkblogdb.ddd.domain.model.player.item.PlayerItem;
import com.inkblogdb.ddd.domain.model.player.item.PlayerItemRepository;
import com.inkblogdb.ddd.domain.model.song.Song;
import com.inkblogdb.ddd.domain.model.song.SongId;
import com.inkblogdb.ddd.domain.model.song.SongRepository;
import com.inkblogdb.ddd.domain.type.Unlockability;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PlayerSongUnlockByTicketHandler implements PlayerSongUnlockHandler {

  private final SongRepository songRepository;
  private final PlayerItemRepository playerItemRepository;

  @Override
  public Unlockability canUnlock(PlayerId playerId, SongId songId) {
    Song song = songRepository.findById(songId);
    ItemId itemId = new ItemId(song.songUnlockTargetId().value());
    PlayerItem playerItem = playerItemRepository.findById(playerId, itemId)
        .orElse(PlayerItem.createNotHave(playerId, itemId));

    return playerItem.canConsume(song.songUnlockQuantity())
        ? Unlockability.UNLOCKABLE
        : Unlockability.LOCKED;
  }

  @Override
  public void executeUnlockAction(PlayerId playerId, SongId songId) {
    Song song = songRepository.findById(songId);
    ItemId itemId = new ItemId(song.songUnlockTargetId().value());
    PlayerItem playerItem = playerItemRepository.findById(playerId, itemId)
        .orElse(PlayerItem.createNotHave(playerId, itemId));
    playerItem.consume(song.songUnlockQuantity());
    playerItemRepository.updateQuantity(playerItem);
  }

}
