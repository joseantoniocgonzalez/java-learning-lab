package org.jose.learning.collections;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserDirectoryTest {

  @Test
  void shouldNormalizeEmail() {
    User u = new User("  Jose@Example.com ");
    assertEquals("jose@example.com", u.email());
  }

  @Test
  void shouldRejectInvalidEmail() {
    assertThrows(IllegalArgumentException.class, () -> new User(" "));
    assertThrows(IllegalArgumentException.class, () -> new User("no-at-symbol"));
  }

  @Test
  void shouldFindUsersByDomainSortedByEmail() {
    UserDirectory dir = new UserDirectory();
    dir.add(new User("b@acme.com"));
    dir.add(new User("a@acme.com"));
    dir.add(new User("x@other.com"));

    List<User> result = dir.findByDomain("acme.com");
    assertEquals(List.of(new User("a@acme.com"), new User("b@acme.com")), result);
  }

  @Test
  void shouldReturnEmptyListForBlankDomain() {
    UserDirectory dir = new UserDirectory();
    assertEquals(List.of(), dir.findByDomain(" "));
    assertEquals(List.of(), dir.findByDomain(null));
  }
}
