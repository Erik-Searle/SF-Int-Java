package threading;

class MyTask2 implements Runnable {
  public volatile long counter = 0;

  @Override
  public void run() {
    for (int i = 0; i < 1_000_000; i++) {
      counter++;
    }
  }
}

public class SimpleRunnable2 {
  public static void main(String[] args) throws Throwable {
    MyTask2 r = new MyTask2();
    System.out.println(Thread.currentThread().getName() + " about to run run()");
//    r.run();
    Thread t1 = new Thread(r);
    t1.start(); // NOT t1.run() :)
    Thread t2 = new Thread(r);
    t2.start();
//    Thread.sleep(1_000);
    t1.join();
    t2.join(); // last action of at thread happens-before 'noticing" that a thread has died
    System.out.println("Counter value is " + r.counter);
    System.out.println(Thread.currentThread().getName() + " ending");
  }
}
