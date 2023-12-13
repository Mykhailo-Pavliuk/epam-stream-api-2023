package com.epam.pavliuk.data;

import java.util.stream.Stream;

public class StreamGenerator {

  public static Stream<String> getWords() {
    return Stream.of("monkey", "gorilla", "bonobo", "monkey");
  }

  public static Stream<String> getChars() {
    return Stream.of("w", "o", "l", "f");
  }

  public static Stream<Integer> getInfinite() {
    return Stream.iterate(1, n -> n+1);
  }

}
