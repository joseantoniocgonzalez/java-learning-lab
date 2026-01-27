package org.jose.learning.collections;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class WordCounterTest {

  @Test
  void shouldReturnEmptyMapForBlankInput() {
    WordCounter wc = new WordCounter();
    assertEquals(Map.of(), wc.countWords(""));
    assertEquals(Map.of(), wc.countWords("   "));
    assertEquals(Map.of(), wc.countWords(null));
  }

  @Test
  void shouldCountWordsCaseInsensitive() {
    WordCounter wc = new WordCounter();
    Map<String, Integer> result = wc.countWords("Java java JAVA");
    assertEquals(3, result.get("java"));
  }

  @Test
  void shouldCountMultipleWords() {
    WordCounter wc = new WordCounter();
    Map<String, Integer> result = wc.countWords("one two two three three three");
    assertEquals(1, result.get("one"));
    assertEquals(2, result.get("two"));
    assertEquals(3, result.get("three"));
  }
}
