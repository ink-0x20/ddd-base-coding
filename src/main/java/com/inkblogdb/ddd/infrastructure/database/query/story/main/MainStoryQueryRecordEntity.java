package com.inkblogdb.ddd.infrastructure.database.query.story.main;

import java.time.LocalDateTime;
import java.util.UUID;

public record MainStoryQueryRecordEntity(
    UUID id,
    String name,
    int chapter,
    int episode,
    LocalDateTime readAt
) {

}
