package service;

import java.util.HashMap;
import model.Account;

public class BankingService {

    private HashMap<Integer, Account> accounts;

    public BankingService() {
        accounts = new HashMap<>();
    }

    public void createAccount(int accountNumber) {
        if (accounts.containsKey(accountNumber)) {
            System.out.println("Account already exists!");
        } else {
            accounts.put(accountNumber, new Account(accountNumber));
            System.out.println("Account created successfully!");
        }
    }

    public Account getAccount(int accountNumber) {
        return accounts.get(accountNumber);
    }
}