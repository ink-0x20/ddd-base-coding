package com.inkblogdb.ddd.domain.model.song;

import com.inkblogdb.ddd.domain.model.player.PlayerId;

public interface SongRepository {

  Songs findAll(PlayerId playerId);

}
