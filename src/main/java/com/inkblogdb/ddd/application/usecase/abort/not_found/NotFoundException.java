package com.inkblogdb.ddd.application.usecase.abort.not_found;

import com.inkblogdb.ddd.application.usecase.abort.AbortException;

public class NotFoundException extends AbortException {

  public NotFoundException(String message) {
    super(message);
  }

}
