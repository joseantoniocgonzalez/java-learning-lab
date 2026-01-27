package org.jose.learning.collections;

import java.util.HashMap;
import java.util.Map;

public class WordCounter {

  public Map<String, Integer> countWords(String text) {
    if (text == null || text.isBlank()) {
      return Map.of();
    }

    String[] tokens = text.trim().toLowerCase().split("\\s+");
    Map<String, Integer> counts = new HashMap<>();

    for (String token : tokens) {
      counts.put(token, counts.getOrDefault(token, 0) + 1);
    }

    return counts;
  }
}
