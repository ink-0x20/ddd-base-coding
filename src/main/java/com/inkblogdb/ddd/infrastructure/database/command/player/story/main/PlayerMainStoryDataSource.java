package com.inkblogdb.ddd.infrastructure.database.command.player.story.main;

import com.inkblogdb.ddd.domain.model.player.PlayerId;
import com.inkblogdb.ddd.domain.model.player.story.main.PlayerMainStory;
import com.inkblogdb.ddd.domain.model.player.story.main.PlayerMainStoryRepository;
import com.inkblogdb.ddd.domain.model.story.StoryId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PlayerMainStoryDataSource implements PlayerMainStoryRepository {

  private final PlayerMainStoryMapper playerMainStoryMapper;
  private final PlayMainStoryDomainFactory playMainStoryDomainFactory;

  @Override
  public Optional<PlayerMainStory> findById(PlayerId playerId, StoryId storyId) {
    return playMainStoryDomainFactory.createFrom(
        playerMainStoryMapper.findById(playerId.value(), storyId.value())
    );
  }

}
