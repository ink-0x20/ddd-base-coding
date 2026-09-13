package com.inkblogdb.ddd.application.usecase.abort;

public class NotFoundException extends AbortException {

  public NotFoundException(String message) {
    super(message);
  }

}
