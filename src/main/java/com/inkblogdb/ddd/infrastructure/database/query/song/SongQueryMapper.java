package com.inkblogdb.ddd.infrastructure.database.query.song;

import java.util.List;

public interface SongQueryMapper {

  List<SongQueryRecordEntity> findAll(String playerId);

}
