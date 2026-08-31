package com.inkblogdb.ddd.domain;

public class DomainException extends RuntimeException {

  public DomainException(String message) {
    super(message);
  }

}
