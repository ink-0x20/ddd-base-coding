package com.inkblogdb.ddd.infrastructure.database.command.player.story.main;

import com.inkblogdb.ddd.domain.model.player.PlayerId;
import com.inkblogdb.ddd.domain.model.player.story.main.MainStoryReadDateTime;
import com.inkblogdb.ddd.domain.model.player.story.main.PlayerMainStory;
import com.inkblogdb.ddd.domain.model.story.StoryId;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PlayMainStoryDomainFactory {

  public Optional<PlayerMainStory> createFrom(PlayerMainStoryRecordEntity playerMainStoryRecordEntity) {
    if (playerMainStoryRecordEntity == null) {
      return Optional.empty();
    }
    return Optional.of(new PlayerMainStory(
        new PlayerId(playerMainStoryRecordEntity.playerId()),
        new StoryId(playerMainStoryRecordEntity.storyId()),
        new MainStoryReadDateTime(playerMainStoryRecordEntity.readDateTime())
    ));
  }

}
