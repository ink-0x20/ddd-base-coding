package com.inkblogdb.ddd.presentation.api.v1.story;

import java.time.LocalDateTime;

public record MainStoryResponse(
    String id,
    String name,
    int chapter,
    int episode,
    LocalDateTime readAt
) {

}
