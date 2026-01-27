package org.jose.learning.oop;

public class BankAccount {
  private final String iban;
  private int balance;

  public BankAccount(String iban) {
    if (iban == null || iban.isBlank()) {
      throw new IllegalArgumentException("iban must not be blank");
    }
    this.iban = iban;
    this.balance = 0;
  }

  public String iban() {
    return iban;
  }

  public int balance() {
    return balance;
  }

  public void deposit(int amount) {
    if (amount <= 0) {
      throw new IllegalArgumentException("deposit amount must be positive");
    }
    balance += amount;
  }

  public void withdraw(int amount) {
    if (amount <= 0) {
      throw new IllegalArgumentException("withdraw amount must be positive");
    }
    if (amount > balance) {
      throw new IllegalStateException("insufficient funds");
    }
    balance -= amount;
  }
}
