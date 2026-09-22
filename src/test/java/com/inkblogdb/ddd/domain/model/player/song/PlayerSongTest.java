package com.inkblogdb.ddd.domain.model.player.song;

import com.inkblogdb.ddd.domain.model.player.PlayerId;
import com.inkblogdb.ddd.domain.type.UnlockStatus;
import com.inkblogdb.ddd.domain.model.song.SongId;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class PlayerSongTest {

  @Test
  void アンロック状態に変化すること() {
    // given
    PlayerId playerId = PlayerId.generate();
    SongId songId = SongId.generate();
    SongUnlockedDateTime songUnlockedDateTime = new SongUnlockedDateTime(
        LocalDateTime.of(2000, 1, 1, 0, 0, 0)
    );
    var playerSong = new PlayerSong(
        playerId,
        songId,
        UnlockStatus.LOCKED,
        songUnlockedDateTime
    );

    // when
    playerSong.unlock();

    // then
    assertEquals(playerId, playerSong.playerId());
    assertEquals(songId, playerSong.songId());
    assertEquals(UnlockStatus.UNLOCKED, playerSong.unlockStatus());
    assertNotEquals(songUnlockedDateTime.value(), playerSong.songUnlockedDateTime().value());
  }

}
