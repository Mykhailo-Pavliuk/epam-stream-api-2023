package com.epam.pavliuk;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class CommonInterfacesExample {

  public static void main(String[] args) {

    // Supplier
    Supplier<LocalDate> s1 = () -> LocalDate.now();
    Supplier<LocalDate> s2 = LocalDate::now;

    System.out.println(s1);
    System.out.println(s1.get());
    System.out.println(s2.get());

    // Consumer
    Consumer<String> c1 = x -> System.out.println(x);
    Consumer<String> c2 = System.out::println;

    c1.accept("Oil");
    c2.accept("Gas");

    // BiConsumer
    var map = new HashMap<String, Integer>();
    BiConsumer<String, Integer> bc1 = (k, v) -> map.put(k, v);
    BiConsumer<String, Integer> bc2 = map::put;

    bc1.accept("chicken", 7);
    bc2.accept("rabbit", 1);

    System.out.println(map);

    // Predicate
    Predicate<String> p1 = x -> x.isEmpty();
    Predicate<String> p2 = String::isEmpty;

    System.out.println(p1.test(""));
    System.out.println(p2.test(""));

    // BiPredicate
    BiPredicate<String, String> bp1 = (string, prefix) -> string.startsWith(prefix);
    BiPredicate<String, String> bp2 = String::startsWith;

    System.out.println(bp1.test("chicken", "chick"));
    System.out.println(bp2.test("chicken", "chick"));

    // Function
    Function<String, Integer> f1 = x -> x.length();
    Function<String, Integer> f2 = String::length;

    System.out.println(f1.apply("clock"));
    System.out.println(f2.apply("clock"));

    // BiFunction
    BiFunction<String, String, String> bf1 = (string, toAdd) -> string.concat(toAdd);
    BiFunction<String, String, String> bf2 = String::concat;

    System.out.println(bf1.apply("baby ", "eagle"));
    System.out.println(bf2.apply("oil and ", "gas"));

    // UnaryOperator
    UnaryOperator<String> u1 = x -> x.toUpperCase();
    UnaryOperator<String> u2 = String::toUpperCase;

    System.out.println(u1.apply("oil"));
    System.out.println(u2.apply("gsd"));

    // BinaryOperator
    BinaryOperator<String> bu1 = (string, toAdd) -> string.concat(toAdd);
    BinaryOperator<String> bu2 = String::concat;

    System.out.println(bu1.apply("baby ", "eagle"));
    System.out.println(bu2.apply("oil and ", "gas"));
  }
}
