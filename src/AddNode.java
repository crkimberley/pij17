/**
 * @author crkimberley on 13/11/2016.
 */
public class AddNode implements Runnable {
    private SelfSortingList2 list;
    private SelfSortingList2.Node node;

    public AddNode(SelfSortingList2 list, SelfSortingList2.Node node) {
        this.list = list;
        this.node = node;
    }

    public void run() {
        SelfSortingList2.Node temp = list.getHead().getNext();
        while (temp.getNext() != null) {
            if (node.getValue() < temp.getNext().getValue()) {
                node.setNext(temp.getNext());
                temp.setNext(node);
                return;
            }
            temp = temp.getNext();
        }
        temp.setNext(node);
    }
}
