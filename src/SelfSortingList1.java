/**
 * @author crkimberley on 07/11/2016.
 */
public class SelfSortingList1 {

    private Node head = new Node();
    private boolean sorted = true;
    private SortAssist sortAssist = new SortAssist(this);
    private Thread thread = new Thread(sortAssist);

    public SelfSortingList1() {
        thread.start();
    }

    public void add(int value) {
        Node tempNode = head;
        while (tempNode.next != null) {
            tempNode = tempNode.next;
        }
        tempNode.next = new Node(value);
        setSorted(false);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void sort() {
        int swapCount = 0;
        for (Node temp = head.next; temp != null && temp.next != null && swapCount < 5; temp = temp.next) {
            if (temp.value > temp.next.value) {
                int tempValue = temp.value;
                temp.value = temp.next.value;
                temp.next.value = tempValue;
                swapCount++;
            }
        }
        printList();
        checkListIsSorted();
    }

    public synchronized void setSorted(boolean sorted) {
        this.sorted = sorted;
        sortAssist.notifySortedChange();
    }

    public void checkListIsSorted() {
        for (Node temp = head.next; temp != null && temp.next != null; temp = temp.next) {
            if (temp.value > temp.next.value) {
                setSorted(false);
                return;
            }
        }
        setSorted(true);
    }

    public boolean isSorted() {
        return sorted;
    }

    public int get(int index) {
        return sortAssist.getValueAtIndexOnceSorted(index);
    }

    public int getValueAtIndex(int index) {
        Node temp = head.getNext();
        for (int count = 0; temp != null; count++) {
            if (count == index) {
                return temp.getValue();
            }
            temp = temp.getNext();
        }
        return -1;
    }

    public void printList() {
        for (Node temp = head.next; temp != null; temp = temp.next) {
            System.out.print(temp.value + " ");
        }
        System.out.println();
    }

    public Thread getThread() {
        return thread;
    }


    private static class SortAssist implements Runnable {
        private SelfSortingList1 selfSortingList1;

        public SortAssist(SelfSortingList1 selfSortingList1) {
            this.selfSortingList1 = selfSortingList1;
        }

        @Override
        public synchronized void run() {
            for (;;) {
                while (selfSortingList1.isSorted()) {
                    try {
                        wait();
                    } catch (InterruptedException ex) {
                        System.out.println("\nSortAssist thread interrupted");
                        return;
                    }
                }
                selfSortingList1.sort();
            }
        }

        public synchronized void notifySortedChange() {
            notifyAll();
        }

        public synchronized int getValueAtIndexOnceSorted(int index) {
            while (!selfSortingList1.isSorted()) {
                try {
                    wait();
                } catch (InterruptedException ex) {
                    // wait less
                }
            }
            return selfSortingList1.getValueAtIndex(index);
        }
    }


    private static class Node {
        private int value;
        private Node next = null;

        public Node() {}

        public Node(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public Node getNext() {
            return next;
        }
    }
}