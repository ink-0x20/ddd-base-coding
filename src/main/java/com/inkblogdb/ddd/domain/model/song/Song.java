package com.inkblogdb.ddd.domain.model.song;

public record Song(
    SongId songId,
    SongName songName,
    SongLevelEasy songLevelEasy,
    SongLevelNormal songLevelNormal,
    SongLevelHard songLevelHard,
    SongLevelExpert songLevelExpert,
    SongUnlockType songUnlockType,
    SongUnlockTargetId songUnlockTargetId,
    SongUnlockQuantity songUnlockQuantity,
    SongReleaseDateTime songReleaseDateTime) {

}
