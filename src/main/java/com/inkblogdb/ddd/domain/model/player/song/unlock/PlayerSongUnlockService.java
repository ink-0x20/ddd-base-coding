package com.inkblogdb.ddd.domain.model.player.song.unlock;

import com.inkblogdb.ddd.domain.model.player.PlayerId;
import com.inkblogdb.ddd.domain.model.player.song.PlayerSong;
import com.inkblogdb.ddd.domain.model.player.song.PlayerSongRepository;
import com.inkblogdb.ddd.domain.model.song.SongId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlayerSongUnlockService {

  private final PlayerSongRepository playerSongRepository;

  public PlayerSong findById(PlayerId playerId, SongId songId) {
    return playerSongRepository.findById(playerId, songId)
        .orElse(PlayerSong.createWithLocked(
            playerId,
            songId
        ));
  }

}
