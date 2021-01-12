package superiterable;

import students.Student;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class SuperIterable<E> implements Iterable<E> {
  private Iterable<E> self;

  public SuperIterable(Iterable<E> self) {
    this.self = self;
  }

  public <F> SuperIterable<F> map(Function<E, F> op) {
    List<F> out = new ArrayList<>();
    for (E e : self) {
      out.add(op.apply(e));
    }
    return new SuperIterable<>(out);
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

      System.out.println("-------------------");
      sis
          .filter(w -> w.length() > 3)
//          .map(w -> w.toUpperCase())
          .map(String::toUpperCase)
          .forEach(System.out::println);

      System.out.println("-------------------");
      sis
          .filter(w -> w.length() > 3)
          .map(w -> {
            System.out.println("> " + w);
            return null;
          })
      .forEach(System.out::println);
    }

    /*
    Lab:
    Create a SuperIterable<Student> use it to print out
     - all smart students in the form <name> has grade <grade>
     - all the "less enthusiastic students" with a message <student.toString> isn't very enthusiastic
     - all students in the form <name> takes [list of courses]

     IN EVERY CASE the LAST element in the "pipeline" MUST BE EXACTLY
     .forEach(System.out::println);
     */
  }
}
