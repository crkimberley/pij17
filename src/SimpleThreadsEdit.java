public class SimpleThreadsEdit {

    public static void main(String args[]) throws InterruptedException {
        long patience = 1000 * 3;
        threadMessage("Starting MessageLoop thread");
        long startTime = System.currentTimeMillis();
        Thread t = new Thread(new MessageLoop());
        t.start();
        threadMessage("Waiting for MessageLoop thread to finish - check t.isAlive() every second");
        while (t.isAlive()) {
            threadMessage("Still waiting...");
            t.join(1000);
            if (((System.currentTimeMillis() - startTime) > patience) && t.isAlive()) {
                threadMessage("Tired of waiting! Let's interrupt the thread");
                t.interrupt();
                t.join();
            }
        }
        threadMessage("Finally! Thread finished or interrupted");
    }

    private static class MessageLoop implements Runnable {
        public void run() {
            String importantInfo[] = {"Message1", "Message2", "Message3", "Message4"};
            try {
                for (String anImportantInfo : importantInfo) {
                    Thread.sleep(1000);
                    threadMessage(anImportantInfo);
                }
            } catch (InterruptedException e) {
                threadMessage("MessageLoop thread INTERRUPTED! I wasn't done!");
            }
        }
    }

    static void threadMessage(String message) {
        System.out.format("%s: %s%n", Thread.currentThread().getName(), message);
    }
}