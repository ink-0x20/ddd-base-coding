package com.inkblogdb.ddd.application.query.song;

import com.inkblogdb.ddd.domain.model.player.PlayerId;

import java.util.List;

public interface SongQueryService {

  List<SongDTO> findAll(PlayerId playerId);

}
