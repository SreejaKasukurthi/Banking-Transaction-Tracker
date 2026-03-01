import java.util.Scanner;
import exception.InsufficientBalanceException;
import model.Account;
import service.BankingService;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        BankingService bankingService = new BankingService();

        while (true) {

            System.out.println("\n===== Banking System =====");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Undo Last Transaction");
            System.out.println("5. Show Transactions");
            System.out.println("6. Show Balance");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");

            int choice;

            try {
                choice = sc.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid input! Enter a number.");
                sc.nextLine();
                continue;
            }

            if (choice == 7) {
                System.out.println("Thank you!");
                break;
            }

            System.out.print("Enter Account Number: ");
            int accNo = sc.nextInt();

            Account account;

            switch (choice) {

                case 1:
                    bankingService.createAccount(accNo);
                    break;

                case 2:
                    account = bankingService.getAccount(accNo);
                    if (account != null) {
                        System.out.print("Enter amount: ");
                        account.deposit(sc.nextDouble());
                    } else {
                        System.out.println("Account not found!");
                    }
                    break;

                case 3:
                    account = bankingService.getAccount(accNo);
                    if (account != null) {
                        System.out.print("Enter amount: ");
                        try {
                            account.withdraw(sc.nextDouble());
                        } catch (InsufficientBalanceException e) {
                            System.out.println(e.getMessage());
                        }
                    } else {
                        System.out.println("Account not found!");
                    }
                    break;

                case 4:
                    account = bankingService.getAccount(accNo);
                    if (account != null) {
                        account.undoLastTransaction();
                    } else {
                        System.out.println("Account not found!");
                    }
                    break;

                case 5:
                    account = bankingService.getAccount(accNo);
                    if (account != null) {
                        account.showTransactions();
                    } else {
                        System.out.println("Account not found!");
                    }
                    break;

                case 6:
                    account = bankingService.getAccount(accNo);
                    if (account != null) {
                        System.out.println("Current Balance: ₹" + account.getBalance());
                    } else {
                        System.out.println("Account not found!");
                    }
                    break;

                default:
                    System.out.println("Invalid choice!");
            }
        }

        sc.close();
    }
}