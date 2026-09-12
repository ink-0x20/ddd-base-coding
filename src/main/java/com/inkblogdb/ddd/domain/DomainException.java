package com.inkblogdb.ddd.domain;

public class DomainException extends RuntimeException implements CustomException {

  public DomainException(String message) {
    super(message);
  }

  @Override
  public String message() {
    return getMessage();
  }

}
