package org.jose.learning.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoneyTest {

  @Test
  void shouldCreateMoney() {
    Money m = new Money(10, "EUR");
    assertEquals(10, m.amount());
    assertEquals("EUR", m.currency());
  }

  @Test
  void shouldAddSameCurrency() {
    Money a = new Money(10, "EUR");
    Money b = new Money(5, "EUR");
    assertEquals(new Money(15, "EUR"), a.add(b));
  }

  @Test
  void shouldRejectBlankCurrency() {
    assertThrows(IllegalArgumentException.class, () -> new Money(10, " "));
  }

  @Test
  void shouldRejectDifferentCurrencyAddition() {
    Money eur = new Money(10, "EUR");
    Money usd = new Money(5, "USD");
    assertThrows(IllegalArgumentException.class, () -> eur.add(usd));
  }
}
