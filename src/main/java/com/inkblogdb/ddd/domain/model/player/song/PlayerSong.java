package com.inkblogdb.ddd.domain.model.player.song;

import com.inkblogdb.ddd.domain.model.player.PlayerId;
import com.inkblogdb.ddd.domain.type.UnlockStatus;
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
	private UnlockStatus unlockStatus;
	private PlayerSongUnlockedDateTime playerSongUnlockedDateTime;

	public void unlock() {
		unlockStatus = UnlockStatus.UNLOCKED;
		playerSongUnlockedDateTime = PlayerSongUnlockedDateTime.now();
	}

}
