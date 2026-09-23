package com.inkblogdb.ddd.application.usecase.abort;

public class FailedPlayerSongUnlockException extends AbortException {

  public FailedPlayerSongUnlockException(String message) {
    super(message);
  }

}
