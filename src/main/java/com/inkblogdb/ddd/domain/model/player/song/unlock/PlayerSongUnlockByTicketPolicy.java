package com.inkblogdb.ddd.domain.model.player.song.unlock;

import com.inkblogdb.ddd.domain.model.player.PlayerId;
import com.inkblogdb.ddd.domain.model.song.SongId;
import com.inkblogdb.ddd.domain.type.Unlockability;

public class PlayerSongUnlockByTicketPolicy implements PlayerSongUnlockPolicy {

  @Override
  public Unlockability canUnlock(PlayerId playerId, SongId songId) {
    // TODO: マスタから必要消費量を取得
    // TODO: チケット所持数により解放可能かを判定
    return Unlockability.UNLOCKABLE;
  }

}
