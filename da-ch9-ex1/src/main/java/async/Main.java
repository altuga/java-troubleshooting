package async;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        work03();
    }

    public static void work03() throws InterruptedException, ExecutionException {

        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> 10); // supply an initial
                                                                                                // value of 10
        completableFuture = completableFuture.thenApply(f -> f + 1).thenApply(f -> f + 2);

        System.out.println(completableFuture.get()); // this will print 13
    }

    public static void work02() {

        CompletableFuture<Object> completableFuture = new CompletableFuture<>();
        completableFuture.join();

    }

    public static void work01() {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        Future<Integer> future = executorService.submit(() -> 42);
        System.out.println(future.cancel(false));
    }

}
