package com.inkblogdb.ddd.domain.model.player.story.main;

import com.inkblogdb.ddd.domain.model.player.PlayerId;
import com.inkblogdb.ddd.domain.model.story.StoryId;

import java.util.Optional;

public interface PlayerMainStoryRepository {

  Optional<PlayerMainStory> findById(PlayerId playerId, StoryId storyId);

}
