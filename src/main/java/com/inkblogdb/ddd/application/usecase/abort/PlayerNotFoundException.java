package com.inkblogdb.ddd.application.usecase.abort;

public class PlayerNotFoundException extends NotFoundException {

  public PlayerNotFoundException(String message) {
    super(message);
  }

}
