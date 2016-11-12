import java.util.concurrent.TimeUnit;

/**
 * @author crkimberley on 08/11/2016.
 */
public class SelfSortingListScript {
    public static void main(String[] args) {
        for (int i=0; i<10; i++) {
            System.out.println("\n\nNEW LAUNCH");
            Thread thread = new SelfSortingListScript().launch().getThread();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            thread.interrupt();
        }
    }

    private SelfSortingList launch() {
        SelfSortingList list = new SelfSortingList();

        list.add(9);
        list.add(8);
        list.add(7);
        list.add(6);
        System.out.println("value at index 3 = " + list.get(3));
        list.add(5);
        list.add(4);
        System.out.println("value at index 3 = " + list.get(3));
        list.add(3);
        list.add(2);
        System.out.println("value at index 3 = " + list.get(3));
        list.add(1);
        list.add(0);
        System.out.println("value at index 3 = " + list.get(3));

        return list;
    }
}
