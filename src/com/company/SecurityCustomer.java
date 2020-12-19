package com.company;

public class SecurityCustomer extends Security {
    public boolean login(String pinNumber) {
        return false;
    }

    public static boolean getCredentials(String firstName, String lastName, String accountNumber, String pin) {

        return FileManager.hasCustomer(firstName, lastName, accountNumber, pin);
    }
}
