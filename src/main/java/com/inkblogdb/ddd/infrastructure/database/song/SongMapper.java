package com.inkblogdb.ddd.infrastructure.database.song;

import java.util.List;

public interface SongMapper {

  List<SongRecordEntity> findAll();

}
