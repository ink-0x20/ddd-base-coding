package com.inkblogdb.ddd.domain.model.player.song.unlock;

import com.inkblogdb.ddd.domain.model.player.PlayerId;
import com.inkblogdb.ddd.domain.model.song.Song;
import com.inkblogdb.ddd.domain.model.song.SongId;
import com.inkblogdb.ddd.domain.model.song.SongRepository;
import com.inkblogdb.ddd.domain.model.story.StoryId;
import com.inkblogdb.ddd.domain.type.Unlockability;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PlayerSongUnlockByStoryHandler implements PlayerSongUnlockHandler {

  private final SongRepository songRepository;

  @Override
  public Unlockability canUnlock(PlayerId playerId, SongId songId) {
    Song song = songRepository.findById(songId);
    StoryId storyId = new StoryId(song.songUnlockTargetId().value());
    return Unlockability.LOCKED;
  }

  @Override
  public void executeUnlockAction(PlayerId playerId, SongId songId) {
    // 何もしない
  }

}
