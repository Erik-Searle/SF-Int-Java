package threading;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecServiceLab {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService eService = Executors.newFixedThreadPool(2);
        ArrayList<Future<String>> futures = new ArrayList<>();
        for(int i=0; i<5; i++) {
            int j = i;
            futures.add(eService.submit(() -> {
                try{
                    Thread.sleep(new Random().nextInt(1000) + 1000);
                } catch (InterruptedException e){

                }
                return String.format("Thread %d finished", j);
            }));
        }
            futures.stream().map(f -> {
                try {
                    return f.get();
                } catch (InterruptedException | ExecutionException e) {
                    System.out.println(e);
                    return "";
                }
            }).forEach(System.out::println);
    }
}
