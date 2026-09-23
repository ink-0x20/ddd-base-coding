package com.inkblogdb.ddd.application.usecase.player.song;

public record UnlockSongCommand(String playerId, String songId, String action) {

}
