/**
 * @author crkimberley on 07/11/2016.
 */
public class SelfSortingList {

    private Node head;
    private boolean sorted, stopped;
    private SortAssist sortAssist;
    private Thread thread;

    public SelfSortingList() {
        head = new Node();
        sorted = true;
        stopped = false;
        sortAssist = new SortAssist(this);
        thread = new Thread(sortAssist);
        thread.start();
    }

    public void add(int value) {
        Node tempNode = head;
        while (tempNode.next != null) {
            tempNode = tempNode.next;
        }
        tempNode.next = new Node(value);
        setSorted(false);
    }

    public boolean sort() {
        int swapCount = 0;
        for (Node temp = head.next; temp != null && temp.next != null && swapCount < 10; temp = temp.next) {
            if (temp.value > temp.next.value) {
                int tempValue = temp.value;
                temp.value = temp.next.value;
                temp.next.value = tempValue;
                swapCount++;
            }
        }
        sorted = checkListIsSorted();
        setSorted(sorted);
        printList();
        return sorted;
    }

    public boolean checkListIsSorted() {
        for (Node temp = head.next; temp != null && temp.next != null; temp = temp.next) {
            if (temp.value > temp.next.value) {
                return false;
            }
        }
        return true;
    }

    /*public int get(int index) {
        return sortAssist.getValueAtIndexOnceSorted(index);
    }*/

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

    public boolean isSorted() {
        return sorted;
    }

    public void setSorted(boolean sorted) {
        this.sorted = sorted;
        sortAssist.notifySortedChange();
    }

    public void printList() {
        for (Node temp = head.next; temp != null; temp = temp.next) {
            System.out.print(temp.value + " ");
        }
        System.out.println();
    }

    public boolean isStopped() {
        return stopped;
    }

    public void stop() {
        stopped = true;
        sortAssist.notifySortedChange();
    }

    private static class Node {
        private int value;
        private Node next;

        public Node() {
            this.next = null;
        }

        public Node(int value) {
            this.value = value;
            this.next = null;
        }


        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }
}
