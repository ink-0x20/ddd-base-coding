package com.inkblogdb.ddd.domain.model.player.story.main;

import com.inkblogdb.ddd.domain.model.player.PlayerId;
import com.inkblogdb.ddd.domain.model.story.StoryId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
@AllArgsConstructor
public class PlayerMainStory {

  private final PlayerId playerId;
  private final StoryId storyId;
  private MainStoryReadDateTime mainStoryReadDateTime;

  public static PlayerMainStory createNotHave(PlayerId playerId, StoryId storyId) {
    return new PlayerMainStory(
        playerId,
        storyId,
        MainStoryReadDateTime.createUnread()
    );
  }

  public boolean isRead() {
    return mainStoryReadDateTime.isRead();
  }

}
