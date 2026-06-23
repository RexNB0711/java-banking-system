package banking;

import java.util.ArrayList;

public class Bank {
    private ArrayList<Account> accounts;

    public Bank() {
        accounts = FileManager.loadAccounts();
    }

    public void addAccount(Account account) {
        accounts.add(account);
        System.out.println("Account added successfully.");
    }

    public Account findAccount(String accountNumber) {
        for (Account account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }

    public void displayAllAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts found.");
            return;
        }

        for (Account account : accounts) {
            System.out.println("----------------------");
            account.displayAccountInfo();
        }
    }

    public void transferMoney(String fromAccountNumber, String toAccountNumber, double amount) {
        Account fromAccount = findAccount(fromAccountNumber);
        Account toAccount = findAccount(toAccountNumber);

        if (fromAccount == null || toAccount == null) {
            System.out.println("One or both accounts not found.");
            return;
        }

        if (amount <= 0) {
            System.out.println("Invalid transfer amount.");
            return;
        }

        if (fromAccount.getBalance() >= amount) {
            fromAccount.withdraw(amount);
            toAccount.deposit(amount);
            System.out.println("Transfer completed successfully.");
        } else {
            System.out.println("Insufficient funds for transfer.");
        }
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public void saveAccountsToFile() {
        FileManager.saveAccounts(accounts);
    }
}