package streaming;

import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

class Average {
  private double sum;
  private long count;

  public Average(double sum, long count) {
    this.sum = sum;
    this.count = count;
  }

  public void include(double d) {
    sum += d;
    count++;
  }

  public void merge(Average other) {
    sum += other.sum;
    count += other.count;
  }

  public Optional<Double> get() {
    if (count > 0) {
      return Optional.of(sum / count);
    } else {
      return Optional.empty();
    }
  }
}

public class Averager {
  public static void main(String[] args) {
    long start = System.nanoTime();
    ThreadLocalRandom.current().doubles(5_000_000_000L, -Math.PI, +Math.PI)
        .parallel()
        .collect(
            () -> new Average(0, 0),
            (a, d) -> a.include(d),
            (a1, a2) -> a1.merge(a2)
        )
        .get()
        .map(a -> "The average is " + a)
        .ifPresent(System.out::println);
    long time = System.nanoTime() - start;
    System.out.println("Time was " + (time / 1_000_000_000.0) + " seconds");
  }
}
