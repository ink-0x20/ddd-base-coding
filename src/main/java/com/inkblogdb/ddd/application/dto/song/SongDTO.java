package com.inkblogdb.ddd.application.dto.song;

import java.time.LocalDateTime;

public record SongDTO(
    String id,
    String name,
    int level,
    LocalDateTime releaseDateTime) {

}
