package banking;

import java.io.*;
import java.util.ArrayList;

public class FileManager {
    private static final String FILE_NAME = "accounts.txt";

    public static void saveAccounts(ArrayList<Account> accounts) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Account account : accounts) {
                if (account instanceof SavingsAccount) {
                    SavingsAccount savings = (SavingsAccount) account;
                    writer.println("SAVINGS," +
                            savings.getAccountNumber() + "," +
                            savings.getAccountHolder() + "," +
                            savings.getBalance() + "," +
                            savings.getInterestRate());
                } else if (account instanceof CheckingAccount) {
                    writer.println("CHECKING," +
                            account.getAccountNumber() + "," +
                            account.getAccountHolder() + "," +
                            account.getBalance());
                }
            }
            System.out.println("Accounts saved to file.");
        } catch (IOException e) {
            System.out.println("Error saving accounts: " + e.getMessage());
        }
    }

    public static ArrayList<Account> loadAccounts() {
        ArrayList<Account> accounts = new ArrayList<>();
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            System.out.println("No saved account file found. Starting fresh.");
            return accounts;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                if (parts[0].equals("CHECKING")) {
                    String accountNumber = parts[1];
                    String accountHolder = parts[2];
                    double balance = Double.parseDouble(parts[3]);

                    accounts.add(new CheckingAccount(accountNumber, accountHolder, balance));
                } else if (parts[0].equals("SAVINGS")) {
                    String accountNumber = parts[1];
                    String accountHolder = parts[2];
                    double balance = Double.parseDouble(parts[3]);
                    double interestRate = Double.parseDouble(parts[4]);

                    accounts.add(new SavingsAccount(accountNumber, accountHolder, balance, interestRate));
                }
            }

            System.out.println("Accounts loaded from file.");
        } catch (IOException e) {
            System.out.println("Error loading accounts: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("File contains invalid account data.");
        }

        return accounts;
    }
}