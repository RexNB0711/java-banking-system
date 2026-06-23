package banking;

public class CheckingAccount extends Account {

    public CheckingAccount(String accountNumber, String accountHolder, double balance) {
        super(accountNumber, accountHolder, balance);
    }

    @Override
    public void withdraw(double amount) {
        if (amount > 0 && amount <= getBalance()) {
            setBalance(getBalance() - amount);
            System.out.println("Withdrawal successful from checking account.");
        } else {
            System.out.println("Invalid amount or insufficient funds.");
        }
    }
}