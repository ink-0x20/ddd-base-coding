package com.inkblogdb.ddd.application.query.story.main;

import java.time.LocalDateTime;

public record GetMainStoryModel(
    String id,
    String name,
    int chapter,
    int episode,
    LocalDateTime readAt
) {

}
