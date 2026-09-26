package com.inkblogdb.ddd.infrastructure.database.command.song;

public interface SongMapper {

  SongRecordEntity findById(String songId);

}
