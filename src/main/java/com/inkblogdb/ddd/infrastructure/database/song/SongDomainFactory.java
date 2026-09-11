package com.inkblogdb.ddd.infrastructure.database.song;

import com.inkblogdb.ddd.domain.model.song.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SongDomainFactory {

  public Songs createFrom(List<SongRecordEntity> songRecordEntity) {
    return new Songs(songRecordEntity
        .stream()
        .map(entity -> new Song(
                new SongId(entity.id()),
                new SongName(entity.name()),
                new SongLevel(entity.level()),
                new SongReleaseDateTime(entity.publishAt())
            )
        )
        .toList());
  }

}
