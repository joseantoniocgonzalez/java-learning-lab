package org.jose.learning.exceptions;

public class InvalidAgeException extends RuntimeException {
  public InvalidAgeException(String message) {
    super(message);
  }
}
