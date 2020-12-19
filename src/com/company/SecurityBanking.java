package com.company;

public class SecurityBanking extends Security {

    public boolean login(String pinNumber) {
        // login to check if pinNumber is correct

        return getCredentials(pinNumber);

    }

    public boolean getCredentials(String pinNumber) {

        if (pinNumber.equals("A1234")) {
            System.out.println("Welcome");
            return true;
        } else {
            System.out.println("Invalid pin, exiting...");
            return false;
        }
    }

}
