package async;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class Blocking {
    static ExecutorService executorService = Executors.newFixedThreadPool(5);

    public static void main(String[] args) throws Exception {
        waitForFirstOrAll();
        System.out.println("Finished " + Thread.currentThread().getName());
    }


    public static void waitForFirstOrAll() throws Exception  {
        final Future<String>  api_A =  findAnswersFromAPI("A");
        final Future<String>  api_B =  findAnswersFromAPI("B");
        String a = api_A.get(); // blocking - waste of Thread
        String b = api_B.get(); // blocking - waste of other Thread
        System.out.println(" a " + a + " - " + b );
    }
    
    public static void executorService()  throws Exception {
       
        final Callable<String>  task = () -> getAnswerFromAPI();
        final Future<String> answer = executorService.submit(task);

         System.out.println(answer.get(10, TimeUnit.SECONDS)); // blocking

    }

    
    public static void blockingCall() {
        String answer = getAnswerFromAPI(); // blocking 
        System.out.println(answer);

    }


    public static String getAnswerFromAPI()  {
        try {
            Thread.sleep(ThreadLocalRandom.current().nextLong(1000, 10000));
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println(" " + e );
        }
        return "Answer from A";
    }

    public static Future<String> findAnswersFromAPI(String apiName)  {
        final Callable<String> task =  () -> {
            System.out.println(" --> " +   Thread.currentThread().getName() );
            return  getAnswerFromAPI();};
        return executorService.submit(task);
    }


}
