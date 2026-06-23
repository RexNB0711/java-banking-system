package banking;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Bank bank = new Bank();
        int choice;

        do {
            System.out.println("\n=== Banking System Menu ===");
            System.out.println("1. Create Checking Account");
            System.out.println("2. Create Savings Account");
            System.out.println("3. Deposit Money");
            System.out.println("4. Withdraw Money");
            System.out.println("5. Transfer Money");
            System.out.println("6. Display All Accounts");
            System.out.println("7. Apply Interest to Savings Account");
            System.out.println("8. Save Accounts");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter account number: ");
                    String checkingNumber = input.nextLine();
                    System.out.print("Enter account holder name: ");
                    String checkingHolder = input.nextLine();
                    System.out.print("Enter starting balance: ");
                    double checkingBalance = input.nextDouble();
                    input.nextLine();

                    bank.addAccount(new CheckingAccount(checkingNumber, checkingHolder, checkingBalance));
                    bank.saveAccountsToFile();
                    break;

                case 2:
                    System.out.print("Enter account number: ");
                    String savingsNumber = input.nextLine();
                    System.out.print("Enter account holder name: ");
                    String savingsHolder = input.nextLine();
                    System.out.print("Enter starting balance: ");
                    double savingsBalance = input.nextDouble();
                    System.out.print("Enter interest rate (example 0.05 for 5%): ");
                    double interestRate = input.nextDouble();
                    input.nextLine();

                    bank.addAccount(new SavingsAccount(savingsNumber, savingsHolder, savingsBalance, interestRate));
                    bank.saveAccountsToFile();
                    break;

                case 3:
                    System.out.print("Enter account number: ");
                    String depositNumber = input.nextLine();
                    Account depositAccount = bank.findAccount(depositNumber);

                    if (depositAccount != null) {
                        System.out.print("Enter deposit amount: ");
                        double depositAmount = input.nextDouble();
                        input.nextLine();
                        depositAccount.deposit(depositAmount);
                        bank.saveAccountsToFile();
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;

                case 4:
                    System.out.print("Enter account number: ");
                    String withdrawNumber = input.nextLine();
                    Account withdrawAccount = bank.findAccount(withdrawNumber);

                    if (withdrawAccount != null) {
                        System.out.print("Enter withdrawal amount: ");
                        double withdrawAmount = input.nextDouble();
                        input.nextLine();
                        withdrawAccount.withdraw(withdrawAmount);
                        bank.saveAccountsToFile();
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;

                case 5:
                    System.out.print("Enter source account number: ");
                    String fromAccount = input.nextLine();
                    System.out.print("Enter destination account number: ");
                    String toAccount = input.nextLine();
                    System.out.print("Enter transfer amount: ");
                    double transferAmount = input.nextDouble();
                    input.nextLine();

                    bank.transferMoney(fromAccount, toAccount, transferAmount);
                    bank.saveAccountsToFile();
                    break;

                case 6:
                    bank.displayAllAccounts();
                    break;

                case 7:
                    System.out.print("Enter savings account number: ");
                    String savingsAccountNumber = input.nextLine();
                    Account account = bank.findAccount(savingsAccountNumber);

                    if (account instanceof SavingsAccount) {
                        ((SavingsAccount) account).applyInterest();
                        bank.saveAccountsToFile();
                    } else {
                        System.out.println("Savings account not found.");
                    }
                    break;

                case 8:
                    bank.saveAccountsToFile();
                    break;

                case 0:
                    bank.saveAccountsToFile();
                    System.out.println("Exiting program.");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 0);

        input.close();
    }
}
