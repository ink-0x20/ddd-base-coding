package com.inkblogdb.ddd.application.usecase.abort.not_found;

public class UserNotFoundException extends NotFoundException {

  public UserNotFoundException(String message) {
    super(message);
  }

}
