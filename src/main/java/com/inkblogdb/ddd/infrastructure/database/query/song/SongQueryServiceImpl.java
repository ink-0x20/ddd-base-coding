package com.inkblogdb.ddd.infrastructure.database.query.song;

import com.inkblogdb.ddd.application.query.song.GetSongModel;
import com.inkblogdb.ddd.application.query.song.SongQueryService;
import com.inkblogdb.ddd.domain.model.player.PlayerId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class SongQueryServiceImpl implements SongQueryService {

  private final SongQueryMapper songQueryMapper;

  public List<GetSongModel> findAll(PlayerId playerId) {
    return songQueryMapper.findAll(playerId.value())
        .stream()
        .map(entity -> new GetSongModel(
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
