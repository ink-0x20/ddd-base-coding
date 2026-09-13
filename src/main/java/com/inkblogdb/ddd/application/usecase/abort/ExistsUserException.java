package com.inkblogdb.ddd.application.usecase.abort;

public class ExistsUserException extends AbortException {

  public ExistsUserException(String message) {
    super(message);
  }

}
