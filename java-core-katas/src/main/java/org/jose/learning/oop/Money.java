package org.jose.learning.oop;

import java.util.Objects;

public final class Money {
  private final int amount;
  private final String currency;

  public Money(int amount, String currency) {
    if (currency == null || currency.isBlank()) {
      throw new IllegalArgumentException("currency must not be blank");
    }
    this.amount = amount;
    this.currency = currency;
  }

  public int amount() {
    return amount;
  }

  public String currency() {
    return currency;
  }

  public Money add(Money other) {
    if (!this.currency.equals(other.currency)) {
      throw new IllegalArgumentException("currency mismatch");
    }
    return new Money(this.amount + other.amount, this.currency);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Money money)) return false;
    return amount == money.amount && currency.equals(money.currency);
  }

  @Override
  public int hashCode() {
    return Objects.hash(amount, currency);
  }
}
