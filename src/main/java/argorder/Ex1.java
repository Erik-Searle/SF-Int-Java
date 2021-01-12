package argorder;

interface IntTriConsumer {
  void accept(int a, int b, int c);
}

public class Ex1 {
  public static void showDate(int day1, int month1, int year1) {
    System.out.println("Day is " + day1 + " month is " + month1 + " year is " + year1);
  }

  public static void useTriConsumer(IntTriConsumer itc, int a, int b, int c) {
    itc.accept(a, b, c);
  }
  public static void main(String[] args) {
    showDate(12, 31, 2021);
    useTriConsumer((a, b, c) -> showDate(a, b, c), 12, 31, 2021);
    useTriConsumer(Ex1::showDate, 12, 31, 2021);
  }
}
