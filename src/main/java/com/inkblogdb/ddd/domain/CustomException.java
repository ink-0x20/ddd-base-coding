package com.inkblogdb.ddd.domain;

public interface CustomException {

  default String type() {
    return getClass().getName();
  }

  String message();

  default String detail() {
    return null;
  }

}
