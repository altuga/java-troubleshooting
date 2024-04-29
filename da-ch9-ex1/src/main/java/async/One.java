package async;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class One {

    public static void main(String[] args) throws ExecutionException, InterruptedException, IOException {

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        CompletableFuture<Integer> cf1  = CompletableFuture.supplyAsync(() -> process(1), executorService);
        CompletableFuture<Integer> cf2 = CompletableFuture.supplyAsync(() -> process(2), executorService);
        executorService.shutdown();
    }

    public static int process(int value) {
        try {
            Thread.sleep(1000);
            System.out.println("Thread " + Thread.currentThread().getName() );
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return value;
    }
}
