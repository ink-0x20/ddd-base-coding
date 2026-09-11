package com.inkblogdb.ddd.presentation.api.v1.song;

import java.time.LocalDateTime;

public record SongResponse(
    String id,
    String name,
    int level,
    LocalDateTime releaseDateTime) {

}
