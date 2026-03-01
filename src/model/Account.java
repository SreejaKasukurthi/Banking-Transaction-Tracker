package model;

import java.util.Stack;
import exception.InsufficientBalanceException;

public class Account {

    private int accountNumber;
    private double balance;
    private Stack<Transaction> transactions;

    public Account(int accountNumber) {
        this.accountNumber = accountNumber;
        this.balance = 0;
        this.transactions = new Stack<>();
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid deposit amount.");
            return;
        }
        balance += amount;
        transactions.push(new Transaction("Deposit", amount));
        System.out.println("Deposit successful.");
    }

    public void withdraw(double amount) throws InsufficientBalanceException {
        if (amount <= 0) {
            System.out.println("Invalid withdrawal amount.");
            return;
        }

        if (amount > balance) {
            throw new InsufficientBalanceException("Insufficient balance!");
        }

        balance -= amount;
        transactions.push(new Transaction("Withdraw", amount));
        System.out.println("Withdrawal successful.");
    }

    public void undoLastTransaction() {
        if (transactions.isEmpty()) {
            System.out.println("No transactions to undo.");
            return;
        }

        Transaction last = transactions.pop();

        if (last.getType().equals("Deposit")) {
            balance -= last.getAmount();
        } else {
            balance += last.getAmount();
        }

        System.out.println("Last transaction undone: " + last);
    }

    public void showTransactions() {
        if (transactions.isEmpty()) {
            System.out.println("No transactions available.");
            return;
        }

        System.out.println("Transaction History:");
        for (Transaction t : transactions) {
            System.out.println(t);
        }
    }
}
