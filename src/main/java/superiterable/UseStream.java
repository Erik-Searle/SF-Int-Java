package superiterable;

import students.Student;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class UseStream {
  public static void main(String[] args) {
    List<String> sis = List.of("Fred", "Jim", "Sheila");
    sis.stream()
        .filter(s -> s.length() > 3)
        .forEach(System.out::println);


    System.out.println("-------------------");
    sis.stream()
        .filter(w -> w.length() > 3)
//          .map(w -> w.toUpperCase())
        .map(String::toUpperCase)
        .forEach(System.out::println);

    System.out.println("-------------------");
    sis.stream()
        .filter(w -> w.length() > 3)
        .map(w -> {
          System.out.println("> " + w);
          return null;
        })
        .forEach(System.out::println);

    List<Student> roster = List.of(
        Student.of("Fred", 3.6, "Math", "Physics"),
        Student.of("Jim", 2.2, "Art"),
        Student.of("Jeremy", 2.5, "Art", "Political Science", "Journalism"),
        Student.of("Sheila", 3.9, "Math", "Physics", "Quantum Mechanics", "Astrophysics")
    );
    roster.stream()
        .filter(s -> s.getGrade() > 3)
        .map(s -> String.format("%s has grade %f", s.getName(), s.getGrade()))
        .forEach(System.out::println);

//    "less enthusiastic students" with a message <student.toString> isn't very enthusiastic
    roster.stream()
        .filter(s -> s.getCourses().size() < 4)
        .map(s -> s + " isn't very enthusiastic")
        .forEach(System.out::println);

//    all students in the form <name> takes [list of courses]
    roster.stream()
        .map(s -> s.getName() + " takes " + s.getCourses())
        .forEach(System.out::println);

    roster.stream()
//        .map(s -> s.getCourses())
        .flatMap(s -> s.getCourses().stream())
        .forEach(System.out::println);

    System.out.println("------------------");
    Stream<String> useOnce = sis.stream();
    useOnce.filter(s -> s.length() > 3).forEach(System.out::println);
    System.out.println("------------------");
    sis.stream().filter(s -> s.length() > 3).forEach(System.out::println);
  }
}
