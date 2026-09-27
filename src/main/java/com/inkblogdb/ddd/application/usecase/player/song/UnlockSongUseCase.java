package com.inkblogdb.ddd.application.usecase.player.song;

import com.inkblogdb.ddd.application.usecase.abort.FailedPlayerSongUnlockException;
import com.inkblogdb.ddd.domain.model.player.PlayerId;
import com.inkblogdb.ddd.domain.model.player.song.PlayerSong;
import com.inkblogdb.ddd.domain.model.player.song.PlayerSongRepository;
import com.inkblogdb.ddd.domain.model.player.song.PlayerSongService;
import com.inkblogdb.ddd.domain.model.player.song.unlock.PlayerSongUnlockAction;
import com.inkblogdb.ddd.domain.model.player.song.unlock.PlayerSongUnlockHandleFactory;
import com.inkblogdb.ddd.domain.model.player.song.unlock.PlayerSongUnlockHandler;
import com.inkblogdb.ddd.domain.model.song.SongId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class UnlockSongUseCase {

  private final PlayerSongRepository playerSongRepository;
  private final PlayerSongService playerSongService;
  private final PlayerSongUnlockHandleFactory playerSongUnlockHandleFactory;

  @Transactional(isolation = Isolation.READ_COMMITTED)
  public void unlock(UnlockSongCommand unlockSongCommand) throws FailedPlayerSongUnlockException {
    PlayerId playerId = new PlayerId(unlockSongCommand.playerId());
    SongId songId = new SongId(unlockSongCommand.songId());
    PlayerSongUnlockAction playerSongUnlockAction = PlayerSongUnlockAction.createFrom(unlockSongCommand.action());
    // プレイヤーの楽曲解放状況を取得
    PlayerSong playerSong = playerSongService.findById(playerId, songId);
    if (playerSong.unlockStatus().unlocked()) {
      // 楽曲が解放済みであれば終了
      return;
    }

    // 楽曲解放操作ごとのハンドラーを取得
    PlayerSongUnlockHandler playerSongUnlockHandler = playerSongUnlockHandleFactory.createHandler(playerSongUnlockAction);
    if (playerSongUnlockHandler.canUnlock(playerId, songId).isLocked()) {
      throw new FailedPlayerSongUnlockException("プレイヤー楽曲の解放に失敗");
    }

    // 楽曲解放に必要素材を消費
    playerSongUnlockHandler.executeUnlockAction(playerId, songId);

    // 楽曲を解放
    playerSong.unlock();
    playerSongRepository.insertUnlockStatus(playerSong);
  }

}
