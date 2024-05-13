package async;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class OneWithoutBlocking {

    public static void main(String[] args) throws ExecutionException, InterruptedException, IOException {

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        CompletableFuture<Integer> cf1 = CompletableFuture.supplyAsync(() -> process(1), executorService)
                .thenApply(result -> {
                    System.out.println("It is ready: " + result + " " + Thread.currentThread().getName());
                    return result;
                });

        CompletableFuture<Integer> cf2 = CompletableFuture.supplyAsync(() -> process(1), executorService)
                .thenApply(result -> {
                    System.out.println("It is ready: " + result + " " + Thread.currentThread().getName());
                    return result;
                });



        executorService.shutdown();

        System.out.println("app is finished ");
    }

    public static int process(int value) {
        try {
            Thread.sleep(1000); // I/O operation
            System.out.println("Thread " + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return value;
    }
}
