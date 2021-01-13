package threading;

public class Stopper {
  static boolean stop = false;

  public static void main(String[] args) throws Throwable {
    new Thread(() -> {
      System.out.println(Thread.currentThread().getName() + " starting");
      while (! stop)
        ;
      System.out.println(Thread.currentThread().getName() + " ending");
    }).start(); // action of starting a thread, happens-before the first action of that thread
    System.out.println("Worker started...");
    Thread.sleep(1_000);
    stop = true;
    System.out.println("Value of stop is " + stop);
    System.out.println("Main exiting...");
  }
}
