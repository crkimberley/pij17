/**
 * @author crkimberley on 07/11/2016.
 */
public class SortAssist implements Runnable {

    private SelfSortingList selfSortingList;

    public SortAssist(SelfSortingList selfSortingList) {
        this.selfSortingList = selfSortingList;
    }

    @Override
    public synchronized void run() {
        while (!selfSortingList.isStopped()) {
            while (selfSortingList.isSorted() && !selfSortingList.isStopped()) {
                try {
                    wait();
                } catch (InterruptedException ex) {}
            }
            selfSortingList.sort();
            notifySortedChange();
        }
    }

    public synchronized void notifySortedChange() {
        notifyAll();
    }

    /*public synchronized int getValueAtIndexOnceSorted(int index) {
        while (!selfSortingList.isSorted()) {
            try {
                wait();
            } catch (InterruptedException ex) {
                // wait less
            }
        }
        return selfSortingList.getValueAtIndex(index);
    }*/
}
