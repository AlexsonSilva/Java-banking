package com.company;

public class SavingsAccount extends Account {
    private double balance;


    public SavingsAccount(String account) {
        this.account = account;
        FileManager.createCustomerAccount(account, AppConstants.SAVING_ACCOUNT);
        balance = FileManager.total(account, AppConstants.SAVING_ACCOUNT);
    }


    public double getBalance() {
        return balance;
    }

    public boolean setBalance(String type, double balance) {
        double newBalance = this.balance;

        if (type.equals("Lodge"))
            newBalance += balance;
        else
            newBalance -= balance;

        if (newBalance < 0)
            return false;

        this.balance = newBalance;

        return true;

    }
}
