package com.inkblogdb.ddd.infrastructure;

import com.inkblogdb.ddd.domain.CustomException;

public class InfrastructureException extends RuntimeException implements CustomException {

  public InfrastructureException(Exception e) {
    super(e.getMessage());
  }

  @Override
  public String message() {
    return getMessage();
  }

}
