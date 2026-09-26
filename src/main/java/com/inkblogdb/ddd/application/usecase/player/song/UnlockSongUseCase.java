package com.inkblogdb.ddd.application.usecase.player.song;

import com.inkblogdb.ddd.application.usecase.abort.FailedPlayerSongUnlockException;
import com.inkblogdb.ddd.domain.model.player.PlayerId;
import com.inkblogdb.ddd.domain.model.player.song.PlayerSong;
import com.inkblogdb.ddd.domain.model.player.song.PlayerSongRepository;
import com.inkblogdb.ddd.domain.model.player.song.unlock.PlayerSongUnlockAction;
import com.inkblogdb.ddd.domain.model.player.song.unlock.PlayerSongUnlockPolicy;
import com.inkblogdb.ddd.domain.model.player.song.unlock.PlayerSongUnlockRule;
import com.inkblogdb.ddd.domain.model.player.song.unlock.PlayerSongUnlockService;
import com.inkblogdb.ddd.domain.model.song.Song;
import com.inkblogdb.ddd.domain.model.song.SongId;
import com.inkblogdb.ddd.domain.model.song.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class UnlockSongUseCase {

  private final PlayerSongRepository playerSongRepository;
  private final PlayerSongUnlockService playerSongUnlockService;
  private final PlayerSongUnlockRule playerSongUnlockRule;
  private final SongRepository songRepository;

  @Transactional(isolation = Isolation.READ_COMMITTED)
  public void unlock(UnlockSongCommand unlockSongCommand) throws FailedPlayerSongUnlockException {
    PlayerId playerId = new PlayerId(unlockSongCommand.playerId());
    SongId songId = new SongId(unlockSongCommand.songId());
    // プレイヤーの楽曲解放状況を取得
    PlayerSongUnlockAction playerSongUnlockAction = PlayerSongUnlockAction.createFrom(unlockSongCommand.action());
    PlayerSong playerSong = playerSongUnlockService.findById(playerId, songId);
    if (playerSong.unlockStatus().unlocked()) {
      // 楽曲が解放済みであれば終了
      return;
    }

    // 楽曲マスタの解放条件を取得
    Song song = songRepository.findById(songId);

    // TODO: 楽曲の解放条件チェック
    PlayerSongUnlockPolicy playerSongUnlockPolicy = playerSongUnlockRule.createPolicy(playerSongUnlockAction);
    if (playerSongUnlockPolicy.canUnlock(playerId, songId).isLocked()) {
      throw new FailedPlayerSongUnlockException("プレイヤー楽曲の解放に失敗");
    }

    // TODO: 必要素材を消費

    // 楽曲を解放
    playerSong.unlock();
    playerSongRepository.insertUnlockStatus(playerSong);
  }

}
