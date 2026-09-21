package com.inkblogdb.ddd.domain.model.song.player;

import com.inkblogdb.ddd.domain.model.player.PlayerId;
import com.inkblogdb.ddd.domain.model.song.SongId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
@AllArgsConstructor
public class PlayerSong {

	private final PlayerId playerId;
	private final SongId songId;
	private SongUnlockStatus songUnlockStatus;
	private SongUnlockedDateTime songUnlockedDateTime;

	public void unlock() {
		songUnlockStatus = SongUnlockStatus.UNLOCKED;
		songUnlockedDateTime = SongUnlockedDateTime.now();
	}

}
