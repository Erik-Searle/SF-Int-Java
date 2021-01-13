package threading;

import java.util.Arrays;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;

public class ProducerConsumerLab {
  public static void main(String[] args) {
    BlockingQueue<int[]> queue = new ArrayBlockingQueue<>(10);
    new Thread(() -> {
      for (int i = 0; i < 10_000; i++) {
        try {
          int[] data = {i, 0}; // "transactionally unsound"
          if (i < 100) Thread.sleep(1);
          data[1] = i; // now, settled into transactionally SOUND state
          if (i == 5_000) data[0] = -99; // test the test!
          queue.put(data);
          data = null; // data is published
        } catch (InterruptedException ie) {
          System.out.println("Odd, something wants producer to shut down...");
        }
      }
      System.out.println("Producer finished...");
    }).start();
    new Thread(() -> {
      for (int i = 0; i < 10_000; i++) {
        try {
          int[] data = queue.take();
          if (data[0] != data[1]) {
            System.out.println("**** ERROR, transactional issue: " + Arrays.toString(data));
          }
          if (data[0] != i) System.out.println("**** ERROR, sequence error at index " + i);
        } catch (InterruptedException ie) {
          System.out.println("Odd, something wants consumer to shut down...");
        }
      }
      System.out.println("Consumer finished...");
    }).start();
  }
}
