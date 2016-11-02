/**
 * @author crkimberley on 02/11/2016.
 */

// Added synchronize statements to supplied code (Ex.3) to ensure correct behaviour

public class BankAccount {

    private int balance = 0;

    // public synchronized int getBalance()...necessary?
    public synchronized int getBalance() {
        return balance;
    }

    public synchronized void deposit(int money) {
        balance = balance + money;
    }

    public synchronized int retrieve(int money) {
        int result = 0;
        if (balance > money) {
            result = money;
        } else {
            result = balance;
        }
        balance = balance - result;
        return result;
    }
}