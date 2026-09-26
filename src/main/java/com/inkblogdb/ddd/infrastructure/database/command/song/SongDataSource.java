package com.inkblogdb.ddd.infrastructure.database.command.song;

import com.inkblogdb.ddd.domain.model.song.Song;
import com.inkblogdb.ddd.domain.model.song.SongId;
import com.inkblogdb.ddd.domain.model.song.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SongDataSource implements SongRepository {

  private final SongMapper songMapper;
  private final SongDomainFactory songDomainFactory;

  @Override
  public Song findById(SongId songId) {
    return songDomainFactory.createFrom(
        songMapper.findById(songId.value())
    );
  }

}
