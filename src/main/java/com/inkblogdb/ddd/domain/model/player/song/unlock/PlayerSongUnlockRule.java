package com.inkblogdb.ddd.domain.model.player.song.unlock;

import com.inkblogdb.ddd.domain.DomainException;
import org.springframework.stereotype.Service;

@Service
public class PlayerSongUnlockRule {

  public PlayerSongUnlockPolicy createPolicy(PlayerSongUnlockAction playerSongUnlockAction) {
    if (playerSongUnlockAction == null) {
      throw new DomainException("プレイヤー楽曲解放操作が空");
    }
    if (playerSongUnlockAction == PlayerSongUnlockAction.UNLOCK_BY_TICKET) {
      return new PlayerSongUnlockByTicketPolicy();
    }
    throw new DomainException("プレイヤー楽曲解放操作が不正");
  }

}
