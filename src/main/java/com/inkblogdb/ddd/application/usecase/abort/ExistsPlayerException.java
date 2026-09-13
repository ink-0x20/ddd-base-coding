package com.inkblogdb.ddd.application.usecase.abort;

public class ExistsPlayerException extends AbortException {

  public ExistsPlayerException(String message) {
    super(message);
  }

}
