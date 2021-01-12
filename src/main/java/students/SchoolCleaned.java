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
    showAll(getByCriterion(roster, s -> s.getGrade() > 2.5));
    // take more than 1 course
    showAll(getByCriterion(roster, s -> s.getCourses().size() > 1));
    // have grades in range 2.5 to 3.7
    showAll(getByCriterion(roster, s -> s.getGrade() > 2.5 && s.getGrade() < 3.7));
    showAll(getByCriterion(roster, s -> {
      double grade = s.getGrade();
      return grade > 2.5 && grade < 3.7;
    }));
    // have grade > 3.7 or take more than 2 classes
    showAll(getByCriterion(roster,
        s -> s.getGrade() > 2.5 && s.getCourses().size() > 2));


    // final exercise:
    // create List<String>
    List<String> names = new ArrayList<>(List.of("Sheila", "Fred", "Jim"));
    // -- order ascending alphabetic
    names.forEach(s -> System.out.println("original: " + s));
    names.sort((v1, v2) -> v1.compareTo(v2));
    names.sort(String::compareTo);
    names.forEach(s -> System.out.println("sorted  : " + s));
    // -- order descending length
    names.sort((v1, v2) -> Integer.compare(v2.length(), v1.length()));
    names.forEach(s -> System.out.println("sorted  : " + s));
    // order student list -- List.of is IMMUTABLE, so you must first create
    // a mutable list wrapped around the students
    List<Student> orderedRoster = new ArrayList<>(roster);
    // -- by ascending grade
    orderedRoster.sort((s1, s2) -> Double.compare(s1.getGrade(), s2.getGrade()));
    orderedRoster.forEach(s -> System.out.println("Student named " + s.getName() +
        " has grade " + s.getGrade()));
  }
}
