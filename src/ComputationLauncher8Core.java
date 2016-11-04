/**
 * @author crkimberley on 02/11/2016.
 */

/**
 * This class launched two heavy computations
 * sequentially first, then in parallel.
 * Assuming there is more than one processor in
 * the machine, parallel computations finish
 * earlier.
 */
public class ComputationLauncher8Core {
    /**
     * How many numbers to process? If too low, there is no noticeable
     * difference.
     */
    // Original value in supplied code 40000000 - too big for MacBook, get "java.lang.OutOfMemoryError: Java heap space"
    public static final int COUNT = 10000000;

    /*
     * The computations to be performed. Stored as fields so
     * both methods (sequential and parallel) act on exactly
     * the same data
     */
    private Computation c1 = null;
    private Computation c2 = null;
    private Computation c3 = null;
    private Computation c4 = null;
    private Computation c5 = null;
    private Computation c6 = null;
    private Computation c7 = null;
    private Computation c8 = null;

    /**
     * The main method that launches the computations
     *
     * @param args command-line arguments, ignored
     */
    public static void main(String args[]) {
        ComputationLauncher8Core c = new ComputationLauncher8Core();
        c.launch();
    }

    private double[] createArray(int size) {
        double[] result = new double[size];
        for (int i = 0; i < result.length; i++)
        {
            result[i] = Math.random();
        }
        return result;
    }

    private void launch() {
        System.out.println("This computer has " + Runtime.getRuntime().availableProcessors() + " processors...or cores perhaps");
        long start, stop;
        c1 = new Computation(createArray(COUNT/8));
        c2 = new Computation(createArray(COUNT/8));
        c3 = new Computation(createArray(COUNT/8));
        c4 = new Computation(createArray(COUNT/8));
        c5 = new Computation(createArray(COUNT/8));
        c6 = new Computation(createArray(COUNT/8));
        c7 = new Computation(createArray(COUNT/8));
        c8 = new Computation(createArray(COUNT/8));
        start = System.currentTimeMillis();
        sequentialComputations();
        stop = System.currentTimeMillis();
        System.out.println("Time without threads: " + (stop - start) + "ms");
        start = System.currentTimeMillis();
        parallelComputations();
        stop = System.currentTimeMillis();
        System.out.println("Time with threads: " + (stop - start) + "ms");
    }

    private void sequentialComputations() {
        c1.run();
        c2.run();
        c3.run();
        c4.run();
        c5.run();
        c6.run();
        c7.run();
        c8.run();
        double result1 = c1.getResult();
        double result2 = c2.getResult();
        double result3 = c3.getResult();
        double result4 = c4.getResult();
        double result5 = c5.getResult();
        double result6 = c6.getResult();
        double result7 = c7.getResult();
        double result8 = c8.getResult();
        System.out.println("Result: " + (result1 + result2 + result3 + result4 + result5 + result6 + result7 + result8));
    }

    private void parallelComputations() {
        Thread t1 = new Thread(c1);
        t1.start();
        Thread t2 = new Thread(c2);
        t2.start();
        Thread t3 = new Thread(c3);
        t3.start();
        Thread t4 = new Thread(c4);
        t4.start();
        Thread t5 = new Thread(c5);
        t5.start();
        Thread t6 = new Thread(c6);
        t6.start();
        Thread t7 = new Thread(c7);
        t7.start();
        Thread t8 = new Thread(c8);
        t8.start();
        double result1 = c1.getResult();
        double result2 = c2.getResult();
        double result3 = c3.getResult();
        double result4 = c4.getResult();
        double result5 = c5.getResult();
        double result6 = c6.getResult();
        double result7 = c7.getResult();
        double result8 = c8.getResult();
        System.out.println("Result: " + (result1 + result2 + result3 + result4 + result5 + result6 + result7 + result8));
    }
}