package com.epam.pavliuk;


import java.util.function.LongPredicate;
import java.util.stream.LongStream;

public class ParallelProcessingExample {

  private static final long NUMBER_OF_ELEMENTS = 100_000_000;

  private static final int NUMBER_OF_RUNS = 10;

  public static void main(String[] args) {
    LongPredicate isDivisibleBySeven = n -> n % 7 == 0;

    System.out.println("Sequential processing");
    performNTimes(NUMBER_OF_RUNS, () -> LongStream.range(1, NUMBER_OF_ELEMENTS)
        .filter(isDivisibleBySeven)
        .count());

    System.out.println("\nParallel processing");
    performNTimes(NUMBER_OF_RUNS, () -> LongStream.range(1, NUMBER_OF_ELEMENTS)
        .parallel()
        .filter(isDivisibleBySeven)
        .count());

  }

  private static void performNTimes(int n, Runnable r) {
    LongStream.range(0, n).forEach(i -> {
          long start = System.nanoTime();
          r.run();
          System.out.println((System.nanoTime() - start) / 1_000_000 + " ms");
        }
    );
  }
}
