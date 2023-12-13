package com.epam.pavliuk;

import com.epam.pavliuk.data.StreamGenerator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class CollectingUsingBasicCollectors {

  public static void main(String[] args) {

    String r1 = StreamGenerator.getWords()
        .collect(Collectors.joining(", "));
    System.out.println(r1);

    Double r2 = StreamGenerator.getWords()
        .collect(Collectors.averagingInt(String::length));
    System.out.println(r2);

    TreeSet<String> r3 = StreamGenerator.getWords()
        .filter(s -> s.startsWith("m"))
        .collect(Collectors.toCollection(TreeSet::new));
    System.out.println(r3);

    Map<Integer, String> r4 = StreamGenerator.getWords()
        .collect(Collectors.toMap(
            String::length,
            k -> k,
            (s1, s2) -> s1 + "," + s2
        ));
    System.out.println(r4);
    System.out.println(r4.getClass());

    Map<Integer, String> r5 = StreamGenerator.getWords()
        .collect(Collectors.toMap(
            String::length,
            k -> k,
            (s1, s2) -> s1 + "," + s2,
            TreeMap::new
        ));
    System.out.println(r5);
    System.out.println(r5.getClass());

    Map<Integer, List<String>> r6 = StreamGenerator.getWords()
        .collect(Collectors.groupingBy(String::length));
    System.out.println(r6);

    Map<Integer, Set<String>> r7 = StreamGenerator.getWords()
        .collect(Collectors.groupingBy(
            String::length,
            Collectors.toSet()
        ));
    System.out.println(r7);

    TreeMap<Integer, Set<String>> r8 = StreamGenerator.getWords()
        .collect(Collectors.groupingBy(
            String::length,
            TreeMap::new,
            Collectors.toSet()
        ));
    System.out.println(r8);


    TreeMap<Integer, List<String>> r9 = StreamGenerator.getWords()
        .collect(Collectors.groupingBy(
            String::length,
            TreeMap::new,
            Collectors.toList()
        ));
    System.out.println(r9);

    Map<Boolean, List<String>> r10 = StreamGenerator.getWords()
        .collect(Collectors.partitioningBy(s -> s.length() <= 5));
    System.out.println(r10);

    Map<Boolean, Set<String>> r11 = StreamGenerator.getWords()
        .collect(Collectors.partitioningBy(
            s -> s.length() <= 5,
            Collectors.toSet()
        ));
    System.out.println(r11);

    Map<Integer, Long> r12 = StreamGenerator.getWords()
        .collect(Collectors.groupingBy(
            String::length,
            Collectors.counting()
        ));
    System.out.println(r12);
  }
}
