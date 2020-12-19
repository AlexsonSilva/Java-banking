//21643 Alexson O Silva
//21643@student.dorset-colle.ie

package com.company;

public class CurrentAccount extends Account {
    private double balance;

    public CurrentAccount(String account) {
        this.account = account;
        FileManager.createCustomerAccount(account, AppConstants.CURRENT_ACCOUNT);
        balance = FileManager.total(account, AppConstants.CURRENT_ACCOUNT);
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
