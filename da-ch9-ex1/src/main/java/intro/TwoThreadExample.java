package intro;

class TwoThreadExample implements Runnable {
    private String message;

    public TwoThreadExample(String message) {
        this.message = message;
    }

    public void run() {
        System.out.println(message);
    }

    public static void main(String[] args) {
        System.out.println(" " + Thread.currentThread().getName());
        Thread threadA = new Thread(new TwoThreadExample("A"));
        Thread threadB = new Thread(new TwoThreadExample("B"));
        threadA.setDaemon(true);
        threadB.setDaemon(true);

        threadA.start();
        threadB.start();
        System.out.println(" finised ...");
    }
}
