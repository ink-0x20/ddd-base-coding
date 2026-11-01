package com.inkblogdb.ddd.infrastructure.database.command.player.story.main;

import java.util.UUID;

public interface PlayerMainStoryMapper {

  PlayerMainStoryRecordEntity findById(String playerId, UUID storyId);

}
