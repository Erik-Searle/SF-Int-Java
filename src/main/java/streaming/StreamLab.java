package streaming;

import students.Student;

import java.util.List;

public class StreamLab {
  public static void main(String[] args) {
  /*
  Using Streams, process Students, print out:
  - all smart students
  - the name of all unenthusiastic students
  - <name> takes <n> courses for all smart students
  - all courses (with duplicates :)
  - all courses (with duplicates removed, and in alphabetical order)
  - all courses taken by enthusiastic students, in upper case
  - <name> takes <course> .. for all name/course pairs
  As before the last statement in each of these must be EXACTLY .forEach(System.out::println)
   */
    List<Student> roster = List.of(
        Student.of("Fred", 3.6, "Math", "Physics"),
        Student.of("Jim", 2.2, "Art"),
        Student.of("Jeremy", 2.5, "Art", "Political Science", "Journalism"),
        Student.of("Sheila", 3.9, "Math", "Physics", "Quantum Mechanics", "Astrophysics")
    );
    System.out.println("all smart ------------------");
    roster.stream()
        .filter(s -> s.getGrade() > 3)
        .forEach(System.out::println);
    System.out.println("name of all enthusiastic ------------------");
    roster.stream()
        .filter(s -> s.getCourses().size() < 3)
//        .map(s -> s.getName())
        .map(Student::getName)
        .forEach(System.out::println);
    System.out.println("all courses ------------------");
    roster.stream()
        .flatMap(s -> s.getCourses().stream())
        .forEach(System.out::println);
    System.out.println("distinct, sorted, courses------------------");
    roster.stream()
        .flatMap(s -> s.getCourses().stream())
        .distinct()
        .sorted()
        .forEach(System.out::println);
    System.out.println("enthusiastic student courses, uppercase ------------------");
    roster.stream()
        .filter(s -> s.getCourses().size() > 2)
        .flatMap(s -> s.getCourses().stream())
        .map(String::toUpperCase)
        .forEach(System.out::println);
    System.out.println("------------------");
    roster.stream()
//        .flatMap((s) -> {
//          return s.getCourses().stream().map((w) -> {
//            return s.getName() + " takes " + w;
//          });
//        })
        .flatMap(s -> s.getCourses().stream().map(w -> s.getName() + " takes " + w))
        .forEach(System.out::println);
    System.out.println("------------------");
  }
}









