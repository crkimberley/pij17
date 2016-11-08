import java.util.concurrent.TimeUnit;

/**
 * @author crkimberley on 08/11/2016.
 */
public class SelfSortingListScript {
    public static void main(String[] args) {
        new SelfSortingListScript().launch();
    }

    private void launch() {
        SelfSortingList list = new SelfSortingList();
        list.add(7);
        list.add(6);
        list.add(5);
        list.add(4);
        list.add(3);
        list.add(2);
        list.add(1);

        list.stop();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        list.printList();
    }
}
