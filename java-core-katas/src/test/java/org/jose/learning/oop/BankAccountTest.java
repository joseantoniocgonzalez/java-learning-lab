package org.jose.learning.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

  @Test
  void shouldCreateAccountWithZeroBalance() {
    BankAccount acc = new BankAccount("ES00TEST0000000000000000");
    assertEquals("ES00TEST0000000000000000", acc.iban());
    assertEquals(0, acc.balance());
  }

  @Test
  void shouldDepositAndIncreaseBalance() {
    BankAccount acc = new BankAccount("ES00TEST0000000000000000");
    acc.deposit(100);
    assertEquals(100, acc.balance());
  }

  @Test
  void shouldWithdrawAndDecreaseBalance() {
    BankAccount acc = new BankAccount("ES00TEST0000000000000000");
    acc.deposit(100);
    acc.withdraw(40);
    assertEquals(60, acc.balance());
  }

  @Test
  void shouldRejectBlankIban() {
    assertThrows(IllegalArgumentException.class, () -> new BankAccount(" "));
  }

  @Test
  void shouldRejectNonPositiveDeposit() {
    BankAccount acc = new BankAccount("ES00TEST0000000000000000");
    assertThrows(IllegalArgumentException.class, () -> acc.deposit(0));
    assertThrows(IllegalArgumentException.class, () -> acc.deposit(-10));
  }

  @Test
  void shouldRejectNonPositiveWithdraw() {
    BankAccount acc = new BankAccount("ES00TEST0000000000000000");
    assertThrows(IllegalArgumentException.class, () -> acc.withdraw(0));
    assertThrows(IllegalArgumentException.class, () -> acc.withdraw(-10));
  }

  @Test
  void shouldRejectWithdrawWhenInsufficientFunds() {
    BankAccount acc = new BankAccount("ES00TEST0000000000000000");
    acc.deposit(50);
    assertThrows(IllegalStateException.class, () -> acc.withdraw(60));
  }
}
