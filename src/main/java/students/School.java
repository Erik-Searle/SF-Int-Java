package students;

import java.util.ArrayList;
import java.util.List;

interface Criterion {
  boolean test(Student s);
}

class SmartStudent implements Criterion {
  @Override
  public boolean test(Student s) {
    return s.getGrade() > 3;
  }
}

public class School {
//  private static double threshold = 3;
//  public static List<Student> getSmartStudents(List<Student> ls, double threshold) {
//    List<Student> out = new ArrayList<>();
//    for (Student s : ls) {
//      if (s.getGrade() > threshold) {
//        out.add(s);
//      }
//    }
//    return List.copyOf(out);
//  }
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
        Student.of("Jeremy", 2.2, "Art", "Political Science", "Journalism"),
        Student.of("Sheila", 3.9, "Math", "Physics", "Quantum Mechanics", "Astrophysics")
    );

//    showAll(getSmartStudents(roster, 3));
    showAll(getByCriterion(roster, new SmartStudent()));

    // print out all the "enthusiastic" students
  }
}
