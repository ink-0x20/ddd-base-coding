package com.inkblogdb.ddd.infrastructure.database.query.song;

import com.inkblogdb.ddd.application.query.song.SongDTO;
import com.inkblogdb.ddd.application.query.song.SongQueryService;
import com.inkblogdb.ddd.domain.model.player.PlayerId;
import com.inkblogdb.ddd.domain.model.song.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class SongQueryServiceImpl implements SongQueryService {

  private final SongQueryMapper songQueryMapper;

  public List<SongDTO> findAll(PlayerId playerId) {
    return songQueryMapper.findAll(playerId.value())
        .stream()
        .map(entity -> new SongDTO(
                entity.id(),
                entity.name(),
                entity.level(),
                entity.publishAt(),
                entity.unlockStatus(),
                entity.unlockedAt()
            )
        )
        .toList();
  }

}
