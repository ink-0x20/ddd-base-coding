package com.inkblogdb.ddd.infrastructure.database.command.player.story.main;

import java.time.LocalDateTime;
import java.util.UUID;

public record PlayerMainStoryRecordEntity(
    String playerId,
    UUID storyId,
    LocalDateTime readDateTime
) {

}
