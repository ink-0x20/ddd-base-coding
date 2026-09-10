package com.inkblogdb.ddd.domain.type;

import com.inkblogdb.ddd.domain.DomainException;

import java.security.SecureRandom;

public abstract class Identifier {

  private static final char[] RANDOM_TABLE =
      "23456789abcdefghijkmnpqrstuvwxyz".toCharArray();
  private static final int SIZE = 8;
  private static final int MASK = 31;
  private static final boolean[] VALID_CHARS = new boolean[128];
  private static final ThreadLocal<SecureRandom> RANDOM_HOLDER =
      ThreadLocal.withInitial(() -> {
        try {
          return SecureRandom.getInstance("DRBG");
        } catch (Exception e) {
          return new SecureRandom();
        }
      });
  static {
    for (char c : RANDOM_TABLE) {
      VALID_CHARS[c] = true;
    }
  }

  private final String value;

  public Identifier(String value) {
    if (value == null || value.isBlank()) {
      throw new DomainException(emptyErrorMessage());
    }
    if (value.length() != SIZE) {
      throw new DomainException(invalidErrorMessage(value));
    }
    for (int i = 0; i < SIZE; i++) {
      char c = value.charAt(i);
      if (c >= 128 || !VALID_CHARS[c]) {
        throw new DomainException(invalidErrorMessage(value));
      }
    }
    this.value = value;
  }

  protected static String random() {
    char[] result = new char[SIZE];
    byte[] bytes = new byte[SIZE];
    RANDOM_HOLDER.get().nextBytes(bytes);
    for (int i = 0; i < SIZE; i++) {
      result[i] = RANDOM_TABLE[bytes[i] & MASK];
    }
    return new String(result);
  }

  protected abstract String emptyErrorMessage();
  protected abstract String invalidErrorMessage(String value);

  public String value() {
    return this.value;
  }

}
