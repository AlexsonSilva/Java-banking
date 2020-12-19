package com.company;

public class Customer {
    String firstName, lastName, email, pin;
    String account;

    CurrentAccount currentAccount;
    SavingsAccount savingsAccount;


    public Customer(String _firstName, String _lastName, String _email) {
        firstName = _firstName;
        lastName = _lastName;
        email = _email;
        account = generateAccount(false);
        pin = generateAccount(true);


        currentAccount = new CurrentAccount(account);
        savingsAccount = new SavingsAccount(account);
    }

    //Generate the account number
    public String generateAccount(boolean isAccount) {
        int countFName = 0;
        int countLName = 0;
        char[] letters = {
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
                'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
        };

        for (int i = 0; i < letters.length; i++) {
            if (Character.toLowerCase(firstName.charAt(0)) == letters[i]) {
                countFName = (i + 1);
            }
            if (Character.toLowerCase(lastName.charAt(0)) == letters[i]) {
                countLName = (i + 1);
            }

        }
        System.out.printf("Creating customer: %s %s\n", firstName, lastName);

        if (!isAccount) {
            return String.format("%c%c-%s-%02d-%02d", Character.toLowerCase(firstName.charAt(0)), Character.toLowerCase(lastName.charAt(0)),
                    (firstName.length() + lastName.length()), countFName, countLName);
        } else {
            return String.format("%02d%02d", countFName, countLName);
        }
    }


    public String getAccount() {
        return account;
    }

    public void setAccount(String _account) {
        account = _account;
    }

    public CurrentAccount getCurrentAccount() {
        return currentAccount;
    }

    public void setCurrentAccount(CurrentAccount _currentAccount) {
        currentAccount = _currentAccount;
    }

    public SavingsAccount getSavingsAccount() {
        return savingsAccount;
    }

    public void setSavingsAccount(SavingsAccount _savingsAccount) {
        savingsAccount = _savingsAccount;
    }
}
