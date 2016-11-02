import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * @author crkimberley on 02/11/2016.
 */
public class ResponsiveUI implements Runnable {
    private final int duration;
    private final int taskNumber;
    private static String finishedTasks = "";
    private static int runningTasks = 0;

    public ResponsiveUI(int taskNumber, int duration) {
        this.taskNumber = taskNumber;
        this.duration = duration;
    }

    public ResponsiveUI() {
        taskNumber = -1;
        duration = 0;
    }

    public static void main(String[] args) {
        new ResponsiveUI().launch();
    }

    private void launch() {
        Scanner input = new Scanner(System.in);
        System.out.println("10 tasks");
        for (int i=0; i<10; i++) {
            System.out.print("Enter duration (in ms) of task " + i + ": ");
            int length = input.nextInt();
            Thread thread = new Thread(new ResponsiveUI(i, length));
            thread.start();
            runningTasks++;
            if (!finishedTasks.equals("")) {
                System.out.println("Finished tasks: " + finishedTasks);
                finishedTasks = "";
            }
        }
        // Program could finish before all tasks are complete
        // so this loop keeps checking whether there are any tasks still running
        while (runningTasks > 0) {
            System.out.println("Tasks still running: " + runningTasks + "\t- checking every second");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        System.out.println("ALL TASKS COMPLETE");
    }

    public void run() {
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (this) {
            finishedTasks += (finishedTasks.equals("") ? taskNumber : ", " + taskNumber);
            runningTasks--;
        }
    }
}