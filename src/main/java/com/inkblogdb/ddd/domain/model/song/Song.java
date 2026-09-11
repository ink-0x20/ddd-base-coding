package com.inkblogdb.ddd.domain.model.song;

public record Song(
    SongId songId,
    SongName songName,
    SongLevel songLevel,
    SongReleaseDateTime songReleaseDateTime) {

}
