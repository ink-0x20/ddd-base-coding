package com.inkblogdb.ddd.application.usecase.abort;

public class UserNotFoundException extends NotFoundException {

  public UserNotFoundException(String message) {
    super(message);
  }

}
