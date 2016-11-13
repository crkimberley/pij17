/**
 * @author crkimberley on 13/11/2016.
 */
public class SelfSortingList2 {

    private Thread thread;

    private Node head = new Node();

    public SelfSortingList2() {}

    public void add(int value) {
        printList();
        Node newNode = new Node(value);
        // add with this method if item should be first in list
        if (head.next != null) {
            if (value > head.next.value) {
                // new thread adds integer at right position
                thread = new Thread(new AddNode(this, newNode));
                thread.start();
                return;
            }
            newNode.next = head.next;
        }
        head.next = newNode;
    }

    public Node getHead() {
        return head;
    }

    public Thread getThread() {
        return thread;
    }

    public void printList() {
        for (Node temp = head.next; temp != null; temp = temp.next) {
            System.out.print(temp.value + " ");
            System.out.print(temp.getNext() == null ? "\n" : "");
        }
    }

    static class Node {
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

        public void setNext(Node node) {
            next = node;
        }
    }
}
