import java.util.concurrent.TimeUnit;

/**
 * @author crkimberley on 13/11/2016.
 */
public class SelfSortingList2Script {

    public static void main(String[] args) {
        new SelfSortingList2Script().launch();
    }

    private void launch() {
        SelfSortingList2 list = new SelfSortingList2();

        list.add(1);
        list.add(0);
        list.add(9);
        list.add(8);
        list.add(7);
        list.add(6);
        list.add(5);
        list.add(4);
        list.add(3);
        list.add(2);

        list.printList();
    }
}
