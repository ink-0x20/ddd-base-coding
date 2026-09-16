package com.inkblogdb.ddd.infrastructure.database.song;

import com.inkblogdb.ddd.domain.model.song.*;
import com.inkblogdb.ddd.infrastructure.InfrastructureException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SongDomainFactory {

  public Songs createFrom(List<SongRecordEntity> songRecordEntity) {
    if (songRecordEntity == null) {
      throw new InfrastructureException("楽曲レコードが存在しない");
    }
    return new Songs(songRecordEntity
        .stream()
        .map(entity -> new Song(
                new SongId(entity.id()),
                new SongName(entity.name()),
                new SongLevel(entity.level()),
                new SongReleaseDateTime(entity.publishAt()),
                SongUnlockStatus.createFrom(entity.unlockStatus()),
                new SongUnlockedDateTime(entity.unlockedAt())
            )
        )
        .toList());
  }

}
