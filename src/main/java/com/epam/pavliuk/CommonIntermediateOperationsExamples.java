package com.epam.pavliuk;

import com.epam.pavliuk.data.StreamGenerator;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class CommonIntermediateOperationsExamples {

  public static void main(String[] args) {

    // filter()
    StreamGenerator.getWords()
        .filter(x -> x.startsWith("m"))
        .forEach(System.out::println);

    // distinct()
    StreamGenerator.getWords()
        .distinct()
        .forEach(System.out::println);

    // limit() and skip()
    StreamGenerator.getWords()
        .skip(5)
        .limit(2)
        .forEach(System.out::println);

    // map()
    StreamGenerator.getWords()
        .map(String::length)
        .forEach(System.out::println);

    // flatMap()
    List<String> zero = List.of();
    var one = List.of("Bonobo");
    var two = List.of("Mama Gorilla", "Baby Gorilla");
    Stream<List<String>> animals = Stream.of(zero, one, two);

    animals.flatMap(m -> m.stream())
        .forEach(System.out::println);

    // sorted()
    StreamGenerator.getWords()
        .sorted()
        .forEach(System.out::println);

    StreamGenerator.getWords()
        .sorted(Comparator.reverseOrder())
        .forEach(System.out::println);

    // peek()
    long count = StreamGenerator.getWords()
        .filter(s -> s.startsWith("g"))
        .peek(System.out::println).count();
    System.out.println(count);
  }
}
