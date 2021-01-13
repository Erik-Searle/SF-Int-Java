package threading;

class MyTask implements Runnable {
  private int i = 0;
  @Override
  public void run() { // entry (compare with p.s.v.main)
    System.out.println(Thread.currentThread().getName() + " starting");

    for (; i < 10_000; i++) {
      System.out.println(Thread.currentThread().getName() + " i is " + i);
    }
    System.out.println(Thread.currentThread().getName() + " ending");
  }
}

public class SimpleRunnable {
  public static void main(String[] args) {
    Runnable r = new MyTask();
    System.out.println(Thread.currentThread().getName() + " about to run run()");
//    r.run();
    Thread t1 = new Thread(r);
    t1.start(); // NOT t1.run() :)
    Thread t2 = new Thread(r);
    t2.start();
    System.out.println(Thread.currentThread().getName() + " ending");
  }
}
