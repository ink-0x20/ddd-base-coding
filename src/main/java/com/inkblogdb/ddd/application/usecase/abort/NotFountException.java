package com.inkblogdb.ddd.application.usecase.abort;

public class NotFountException extends AbortException {

  public NotFountException(String message) {
    super(message);
  }

}
