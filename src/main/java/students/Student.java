package students;

import java.util.List;

public final class Student {
  private final String name;
  private final double grade;
  private final List<String> courses;

  private Student(String name, double grade, List<String> courses) {
    // VALIDATE
    this.name = name;
    this.grade = grade;
    this.courses = courses;
  }

  public static Student of(String name, double grade, String ... courses) {
    return new Student(name, grade, List.of(courses));
  }

  public String getName() {
    return name;
  }

  public double getGrade() {
    return grade;
  }

  public List<String> getCourses() {
    return courses;
  }

  public Student withName(String name) {
    return new Student(name, this.grade, this.courses);
  }

  @Override
  public String toString() {
    return "Student{" +
        "name='" + name + '\'' +
        ", grade=" + grade +
        ", courses=" + courses +
        '}';
  }
/*
  Business domain requirements for a student:
   - each has a name
   - an overall gpa (0-> 4.0 range)
   - names of courses being taken

   "School"..
    - "several" students
    - print out all the "smart" students (you decide what "smart" means).
   */
}
