package com.inkblogdb.ddd.application.usecase.abort.not_found;

public class PlayerNotFoundException extends NotFoundException {

  public PlayerNotFoundException(String message) {
    super(message);
  }

}
