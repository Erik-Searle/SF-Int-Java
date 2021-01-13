package streaming;

import students.Student;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GroupingByExamples {
  public static String getLetterGrade(Student s) {
    double grade = s.getGrade();
    if (grade > 3.5) return "A";
    if (grade > 3) return "B";
    return "C";
  }
  public static void main(String[] args) {
    List<Student> roster = List.of(
        Student.of("Fred", 3.6, "Math", "Physics"),
        Student.of("Freddy", 3.5, "Math", "Physics"),
        Student.of("Frederic", 3.2, "Math", "Physics"),
        Student.of("Jim", 2.2, "Art"),
        Student.of("Jeremy", 2.5, "Art", "Political Science", "Journalism"),
        Student.of("Sheila", 3.9, "Math", "Physics", "Quantum Mechanics", "Astrophysics")
    );

    Map<String, List<Student>> results = roster.stream()
        .collect(Collectors.groupingBy(s -> getLetterGrade(s)));
    results.entrySet().stream()
        .map(e -> String.format("Students with grade %s are %s", e.getKey(), e.getValue()))
        .forEach(System.out::println);

    System.out.println("-------------------");
    roster.stream()
        .collect(Collectors.groupingBy(
            s -> getLetterGrade(s),
            Collectors.counting()))
        .entrySet().stream()
        .forEach(System.out::println);

    System.out.println("-------------------");
    roster.stream()
        .collect(Collectors.groupingBy(
            s -> getLetterGrade(s),
            Collectors.mapping(
                s -> s.getName(),
                Collectors.joining(", "))))
        .entrySet().stream()
        .map(e -> "Students with grade " + e.getKey() + " are " + e.getValue())
        .forEach(System.out::println);


  }
}
