package superiterable;

import students.Student;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

public class SuperIterable<E> implements Iterable<E> {
  private Iterable<E> self;

  public SuperIterable(Iterable<E> self) {
    this.self = self;
  }

  public SuperIterable<E> filter(Predicate<E> crit) {
    List<E> out = new ArrayList<>();
    for (E s : this.self) {
      if (crit.test(s)) {
        out.add(s);
      }
    }
    return new SuperIterable<>(out);
  }

  @Override
  public Iterator<E> iterator() {
    return self.iterator();
  }

  public static void main(String[] args) {
    SuperIterable<String> sis = new SuperIterable<>(List.of("Fred", "Jim", "Sheila"));
    sis
        .filter(s -> s.length() > 3)
        .forEach(System.out::println);

    for (String s : sis) {
      System.out.println("> " + s);
    }
  }
}
