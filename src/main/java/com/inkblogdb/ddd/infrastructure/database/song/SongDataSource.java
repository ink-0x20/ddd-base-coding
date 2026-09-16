package com.inkblogdb.ddd.infrastructure.database.song;

import com.inkblogdb.ddd.domain.model.player.PlayerId;
import com.inkblogdb.ddd.domain.model.song.SongRepository;
import com.inkblogdb.ddd.domain.model.song.Songs;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SongDataSource implements SongRepository {

  private final SongMapper songMapper;
  private final SongDomainFactory songDomainFactory;

  public Songs findAll(PlayerId playerId) {
    return songDomainFactory.createFrom(
        songMapper.findAll(playerId.value())
    );
  }

}
