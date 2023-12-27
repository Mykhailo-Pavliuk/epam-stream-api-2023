package com.epam.pavliuk;

import java.util.Optional;

public class OptionalExample {

  public static void main(String[] args) {
    System.out.println(average(90, 100));
    System.out.println(average());

    Optional<Double> opt = average(90, 100);
    if (opt.isPresent()) {
      System.out.println(opt.get());
    }

    opt.ifPresent(System.out::println);

//    System.out.println(average().orElseThrow(
//        () -> new IllegalStateException()
//    ));

    Optional<Double> emptyResult = average();
    System.out.println(emptyResult.orElse(Math.random()));
    System.out.println(emptyResult.orElseGet(() -> Math.random()));
    System.out.println(emptyResult.orElseThrow());
  }

  public static Optional<Double> average(int... scores) {
    if (scores.length == 0) {
      return Optional.empty();
    }

    int sum = 0;
    for (int score : scores) {
      sum += score;
    }
    return Optional.of((double) sum / scores.length);
  }
}
