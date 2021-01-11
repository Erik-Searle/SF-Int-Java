package students;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@FunctionalInterface
interface Silly {
  boolean banana(Student s);
}

@FunctionalInterface
interface Criterion {
  boolean test(Student s);
//  void bad();
}

class SmartStudent implements Criterion {
  @Override
  public boolean test(Student s) {
    return s.getGrade() > 3;
  }
}

class EnthusiasticStudent implements Criterion {

  @Override
  public boolean test(Student s) {
    return s.getCourses().size() > 2;
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

  // Separate a *behavior* that varies independently and "reassembled" the working parts
  // by passing that behavior as an argument.
  // we use an object for the primary purpose of the behavior it contains.
  // Gang of Four pattern: Command
  // FP: one form of "Higher order function"
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
    // to use a lambda expression:
    // - the context MUST require an object that implements an interface ('coz I say so!)
    // - the interface must declare EXACTLY ONE abstract method
    // - we must only want to implement that one abstract method
    // - what we provide MUST conform to that abstract method's signature
    showAll(getByCriterion(roster, (Student s) -> {
      return s.getGrade() > 3;
    }));

    Criterion obj = (Student s) -> {
      return s.getGrade() > 3;
    };

    // Anonymous class:
    // - instance has a new "this" context
    // - can implement an interface, or subclass a class (abstract or concrete)!!!
    // - can implement or override or define multiple methods

    System.out.println("Type of obj is " + obj.getClass().getName());
    System.out.println("obj instanceof Criterion? " + (obj instanceof Criterion));

    Class<?> clazz = obj.getClass();
    Method [] methods = clazz.getMethods();
    for (Method m : methods) {
      System.out.println("-- " + m);
    }

//    Object obj2 = (Criterion)(Student s) -> {
//      return s.getGrade() > 3;
//    };
//
    Criterion obj2 =
        // may specify redundant arg types
        // if you specify type, ALL args must be specified by type, or none, or all specified by var
//    (Student s) -> {
//    (/*@Deprecated */var s) -> {
        // if the ONLY argument has no type spec then, can elide the parens
//    (s) -> {
//        s -> {
//          return s.getGrade() > 3;
//        };
        // using curlies to right of the arrow, must provide a full method body
        // But if body is simply return <expr>
        // can leave out curlies and everything except <expr>

    s -> s.getGrade() > 3  ; // expression lambda. RHS is ONLY a single expression

    // print out all the "enthusiastic" students
    showAll(getByCriterion(roster, new EnthusiasticStudent()));
    showAll(getByCriterion(roster, s -> s.getCourses().size() < 4));
  }
}
