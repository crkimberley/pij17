/**
 * @author crkimberley on 02/11/2016.
 */

/* CODE SUPPLIED IN QUESTION
public class Counter {

    private int n = 0;

    public void increase() {
        n++;
    }

    public int getCount() {
        return n;
    }
}
*/

// Using supplied code the counter doesn't always finish on 100000 (100 x 1000).
// The n++ operation isn't atomic, so we need to synchronize some of the code.
// We can synchronize the increase() method...

public class Counter {

    private int n = 0;

    public synchronized void increase() {
        n++;
    }

    public int getCount() {
        return n;
    }
}

// Alternatively we can synchronize the appropriate code fragment
// either on the current object: synchronized (this)
// or we can add a dummy object, eg. obj, and synchronize on that...

/*
public class Counter {

    //private Object obj = new Object();

    private int n = 0;

    public void increase() {
        synchronized (this) {   // or synchronized(obj)
            n++;
        }
    }

    public int getCount() {
        return n;
    }
}
*/