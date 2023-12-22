package com.epam.pavliuk;

import com.epam.pavliuk.data.StreamGenerator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CommonTerminalOperationsExamples {

  public static void main(String[] args) {

    // count()
    System.out.println(StreamGenerator.getWords().count());

    // min() and max()
    Optional<String> min = StreamGenerator.getWords()
        .min((s1, s2) -> s1.length() - s2.length());
    min.ifPresent(System.out::println);

    // findAny() and findFirst()
    StreamGenerator.getWords()
        .findAny().ifPresent(System.out::println);

    StreamGenerator.getInfinite()
        .findAny().ifPresent(System.out::println);

    // allMatch(), anyMatch() and noneMatch()
    var list = List.of("monkey", "gorilla", "bonobo");
    Stream<String> infiniteStream = Stream.generate(() -> "chimp");
    Predicate<String> predicate = x -> Character.isLetter(x.charAt(0));

    System.out.println(list.stream().anyMatch(predicate)); // ?
    System.out.println(list.stream().allMatch(predicate)); // ?
    System.out.println(list.stream().noneMatch(predicate)); // ?
    System.out.println(infiniteStream.anyMatch(predicate)); // ?

    // reduce()
    var array = new String[]{"w", "o", "l", "f"};
    var result = "";
    for (var s : array) {
      result = result + s;
    }
    System.out.println(result);

    String charsReduce = StreamGenerator.getLetters().reduce("", (s, c) -> s + c);
    System.out.println(charsReduce);

    Stream<Integer> streamInts = Stream.of(3, 5, 6);
    System.out.println(streamInts.reduce(1, (a, b) -> a * b));

    Stream<String> advancedStream = Stream.of("w", "o", "l", "f!");
    int length = advancedStream.reduce(0, (i, s) -> i + s.length(), (a, b) -> a + b);
    System.out.println(length);

    // collect()
    StringBuilder c1 = StreamGenerator.getWords()
        .collect(
            StringBuilder::new,
            StringBuilder::append,
            StringBuilder::append
        );
    System.out.println(c1);

    TreeSet<String> c2 = StreamGenerator.getWords()
        .collect(
            TreeSet::new,
            TreeSet::add,
            TreeSet::addAll
        );
    System.out.println(c2);

    TreeSet<String> c3 = StreamGenerator.getWords()
        .collect(Collectors.toCollection(TreeSet::new));
    System.out.println(c3);

    Set<String> c4 = StreamGenerator.getWords()
        .collect(Collectors.toSet());
    System.out.println(c4);
  }


}
