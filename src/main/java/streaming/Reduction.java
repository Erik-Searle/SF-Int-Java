package streaming;

import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Reduction {
  public static void main(String[] args) {
    Optional res = Stream.iterate(1, x -> x + 1)
        .limit(10)
        .filter(x -> x > 100)
        .reduce((a, b) -> a + b);
//        .forEach(System.out::println);
    res
        .map(v -> "The sum is " + v)
        .ifPresent(System.out::println);
  }
}
