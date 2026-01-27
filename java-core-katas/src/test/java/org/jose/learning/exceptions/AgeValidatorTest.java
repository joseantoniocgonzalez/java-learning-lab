package org.jose.learning.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AgeValidatorTest {

  @Test
  void shouldAcceptValidAges() {
    AgeValidator v = new AgeValidator();
    assertDoesNotThrow(() -> v.validate(0));
    assertDoesNotThrow(() -> v.validate(18));
    assertDoesNotThrow(() -> v.validate(130));
  }

  @Test
  void shouldRejectNegativeAges() {
    AgeValidator v = new AgeValidator();
    InvalidAgeException ex = assertThrows(InvalidAgeException.class, () -> v.validate(-1));
    assertTrue(ex.getMessage().contains(">= 0"));
  }

  @Test
  void shouldRejectTooLargeAges() {
    AgeValidator v = new AgeValidator();
    InvalidAgeException ex = assertThrows(InvalidAgeException.class, () -> v.validate(131));
    assertTrue(ex.getMessage().contains("<= 130"));
  }
}
