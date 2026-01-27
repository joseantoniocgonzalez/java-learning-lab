package org.jose.learning.collections;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class UserDirectory {
  private final List<User> users = new ArrayList<>();

  public void add(User user) {
    users.add(user);
  }

  public List<User> findByDomain(String domain) {
    if (domain == null || domain.isBlank()) {
      return List.of();
    }
    String d = domain.trim().toLowerCase();

    return users.stream()
        .filter(u -> u.email().endsWith("@" + d))
        .sorted(Comparator.comparing(User::email))
        .collect(Collectors.toList());
  }
}
