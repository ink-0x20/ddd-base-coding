package com.inkblogdb.ddd.domain.model.player.song.unlock;

import com.inkblogdb.ddd.domain.DomainException;
import com.inkblogdb.ddd.domain.model.player.item.PlayerItemRepository;
import com.inkblogdb.ddd.domain.model.player.story.main.PlayerMainStoryRepository;
import com.inkblogdb.ddd.domain.model.song.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlayerSongUnlockHandleFactory {

  private final SongRepository songRepository;
  private final PlayerItemRepository playerItemRepository;
  private final PlayerMainStoryRepository playerMainStoryRepository;

  public PlayerSongUnlockHandler createHandler(PlayerSongUnlockAction playerSongUnlockAction) {
    return switch (playerSongUnlockAction) {
      case UNLOCK_BY_TICKET -> new PlayerSongUnlockByTicketHandler(
          songRepository,
          playerItemRepository
      );
      case UNLOCK_BY_STORY -> new PlayerSongUnlockByStoryHandler(
          songRepository,
          playerMainStoryRepository
      );
      case null -> throw new DomainException("プレイヤー楽曲解放操作が空");
    };
  }

}
