package students;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class SchoolCleaned {
  public static List<Student> getByCriterion(List<Student> ls, Criterion crit) {
    List<Student> out = new ArrayList<>();
    for (Student s : ls) {
      if (crit.test(s)) {
        out.add(s);
      }
    }
    return List.copyOf(out);
  }

  public static void showAll(List<Student> ls) {
    for (Student s : ls) {
      System.out.println("> " + s);
    }
    System.out.println("---------------------");
  }

  public static void main(String[] args) {
    List<Student> roster = List.of(
        Student.of("Fred", 3.6, "Math", "Physics"),
        Student.of("Jim", 2.2, "Art"),
        Student.of("Jeremy", 2.5, "Art", "Political Science", "Journalism"),
        Student.of("Sheila", 3.9, "Math", "Physics", "Quantum Mechanics", "Astrophysics")
    );
    showAll(getByCriterion(roster, s -> s.getCourses().size() < 4));

















    // Lab 3
    // show all student who:
    // have grades above 2.5
    // take more than 1 course
    // have grades in rang 2.5 to 3.7
    // have grade > 3.7 or take more than 2 classes
  }
}
