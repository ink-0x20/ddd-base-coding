package com.inkblogdb.ddd.domain.model.song;

public interface SongRepository {

  Song findById(SongId songId);

}
