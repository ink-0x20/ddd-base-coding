package com.inkblogdb.ddd.application.usecase.abort;

import com.inkblogdb.ddd.domain.CustomException;

public class AbortException extends Exception implements CustomException {

  public AbortException(String message) {
    super(message);
  }

  @Override
  public String message() {
    return getMessage();
  }

}
