package com.inkblogdb.ddd.infrastructure.database.query.story.main;

import com.inkblogdb.ddd.application.query.story.main.GetMainStoryModel;
import com.inkblogdb.ddd.application.query.story.main.MainStoryQueryService;
import com.inkblogdb.ddd.domain.model.player.PlayerId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MainStoryQueryServiceImpl implements MainStoryQueryService {

  private final MainStoryQueryMapper mainStoryQueryMapper;

  @Override
  public List<GetMainStoryModel> findAll(PlayerId playerId) {
    return mainStoryQueryMapper.findAll(playerId.value())
        .stream()
        .map(entity -> new GetMainStoryModel(
            entity.id().toString(),
            entity.name(),
            entity.chapter(),
            entity.episode(),
            entity.readAt()
            )
        )
        .toList();
  }

}
