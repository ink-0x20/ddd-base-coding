package com.inkblogdb.ddd.infrastructure.database.command.song;

import com.inkblogdb.ddd.domain.model.song.*;
import com.inkblogdb.ddd.infrastructure.InfrastructureException;
import org.springframework.stereotype.Component;

@Component
public class SongDomainFactory {

  public Song createFrom(SongRecordEntity songRecordEntity) {
    if (songRecordEntity == null) {
      throw new InfrastructureException("楽曲レコードが存在しない");
    }
    return new Song(
        new SongId(songRecordEntity.id()),
        new SongName(songRecordEntity.name()),
        new SongLevelEasy(songRecordEntity.easyLevel()),
        new SongLevelNormal(songRecordEntity.normalLevel()),
        new SongLevelHard(songRecordEntity.hardLevel()),
        new SongLevelExpert(songRecordEntity.expertLevel()),
        SongUnlockType.createFrom(songRecordEntity.unlockType()),
        new SongUnlockTargetId(songRecordEntity.unlockTargetId()),
        new SongUnlockQuantity(songRecordEntity.unlockQuantity()),
        new SongReleaseDateTime(songRecordEntity.publishAt())
    );
  }

}
