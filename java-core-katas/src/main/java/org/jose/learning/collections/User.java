package org.jose.learning.collections;

import java.util.Objects;

public final class User {
  private final String email;

  public User(String email) {
    if (email == null || email.isBlank() || !email.contains("@")) {
      throw new IllegalArgumentException("invalid email");
    }
    this.email = email.trim().toLowerCase();
  }

  public String email() {
    return email;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof User user)) return false;
    return email.equals(user.email);
  }

  @Override
  public int hashCode() {
    return Objects.hash(email);
  }
}
