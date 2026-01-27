package org.jose.learning.exceptions;

public class AgeValidator {

  public void validate(int age) {
    if (age < 0) {
      throw new InvalidAgeException("age must be >= 0");
    }
    if (age > 130) {
      throw new InvalidAgeException("age must be <= 130");
    }
  }
}
