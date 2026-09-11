package com.inkblogdb.ddd.domain.type;

public abstract class Text {

  private final String value;

  public Text(String value) {
    validate(value);
    this.value = value;
  }

  protected abstract void validate(String value);

  public String value() {
    return this.value;
  }

}
