package com.company;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class FileManager {
    private static Formatter output;
    private static Scanner input;

    //Create the customer txt file
    private static String CustomerFile = "customers.txt";

    //Add the customer to file
    public static void AddCustomerToFile(Customer customer) {
        OpenFileToWrite(CustomerFile);
        output.format("%s %s %s %s %s \n", customer.firstName, customer.lastName, customer.email, customer.account, customer.pin);
        CloseFile();
    }

    //Add transaction to file
    public static void AddTransactionToFile(BankingTransaction _bankingTransaction) {
        if (_bankingTransaction.typeAccount == AppConstants.CURRENT_ACCOUNT) {
            OpenFileToWrite(_bankingTransaction.customer.getAccount() + "-current.txt");

            if (!_bankingTransaction.customer.getCurrentAccount().setBalance(_bankingTransaction.typeTransaction, _bankingTransaction.amount)) {
                System.out.println(" Current balance cannot be < 0");
                CloseFile();
                return;
            }

            output.format("%s %s %f %f %n", date(), _bankingTransaction.typeTransaction, _bankingTransaction.amount, _bankingTransaction.customer.getCurrentAccount().getBalance());

        } else {
            OpenFileToWrite(_bankingTransaction.customer.getAccount() + "-savings.txt");

            if (!_bankingTransaction.customer.getSavingsAccount().setBalance(_bankingTransaction.typeTransaction, _bankingTransaction.amount)) {
                System.out.println("Savings balance cannot be < 0");
                CloseFile();
                return;
            }
            output.format("%s %s %f %f \n", date(), _bankingTransaction.typeTransaction, _bankingTransaction.amount, _bankingTransaction.customer.getSavingsAccount().getBalance());
        }
        CloseFile();
    }

    public static Customer getCustomer(String account) {
        OpenFileToRead();
        try {
            while (input.hasNext()) {
                String _firstName, _lastName, _email, _account;
                _firstName = input.next();
                _lastName = input.next();
                _email = input.next();
                _account = input.next();

                if (account.equals(_account)) {
                    return new Customer(_firstName, _lastName, _email);
                } else {
                    return null;
                }

            }
        } catch (NoSuchElementException elementException) {
            System.err.println("File improperly formed. Terminating");

            System.exit(1);
        } catch (IllegalStateException stateException) {
            System.err.println("Error reading from file. Terminating");

            System.exit(1);

        }
        CloseFile();
        return null;
    }

    //Create customer account
    public static void createCustomerAccount(String customerAccountName, int _type) {
        String filename;

        if (_type == AppConstants.SAVING_ACCOUNT) {
            filename = customerAccountName + "-savings.txt";
        } else {
            filename = customerAccountName + "-current.txt";
        }
        OpenFileToWrite(filename);
        CloseFile();

//        output.format("testing customer account %n");
//        CloseFile();
    }

    //Create list of customers
    public static void ListCustomers() {

        OpenFileToRead();

        try {
            while (input.hasNext()) {
                String firstName, lastName, email, account, pin;
                firstName = input.next(); // first name
                lastName = input.next(); // last name
                email = input.next(); // email
                account = input.next(); // account
                pin = input.next(); // pin

                System.out.printf("%s %s %s %s \n", firstName, lastName, email, account);
            }

        } catch (NoSuchElementException elementException) {
            System.err.println("File improperly formed. Terminating");
            System.exit(1);
        } catch (IllegalStateException stateException) {
            System.err.println("Error reading from file. Terminating");
            System.exit(1);

        }

        CloseFile();


    }

    //List of customers balance
    public static void ListCustomersBalance() {

        OpenFileToRead();

        try {
            while (input.hasNext()) {
                String firstName, lastName, email, account, pin;
                firstName = input.next();
                lastName = input.next();
                email = input.next();
                account = input.next();
                pin = input.next();

                double currentAccount = total(account, AppConstants.CURRENT_ACCOUNT);
                double savingAccount = total(account, AppConstants.SAVING_ACCOUNT);

                System.out.printf("%s %s %s %s current: %f savings: %f %n", firstName, lastName, email, account, currentAccount, savingAccount);
            }
        } catch (NoSuchElementException elementException) {
            System.err.println("File improperly formed. Terminating");
            System.exit(1);
        } catch (IllegalStateException stateException) {
            System.err.println("Error reading from file. Terminating");
            System.exit(1);

        }

        CloseFile();
    }

    private static boolean hasFile(String name) {
        try {
            input = new Scanner(Paths.get(name));
            return true;
        } catch (IOException ioException) {
            System.err.println("Error opening " + name + ".");
            return false;
        }
    }


    public static double total(String account, int _type) {

        String filename;
        double total = 0;

        if (_type == AppConstants.SAVING_ACCOUNT)
            filename = account + "-savings.txt";
        else
            filename = account + "-current.txt";

        if (hasFile(filename))
            OpenFileToRead(filename);
        else
            return total;


        try {
            while (input.hasNext()) {
                String date, transaction;
                double amount, balance;
                date = input.next();
                transaction = input.next();
                amount = input.nextDouble();
                balance = input.nextDouble();

                total += amount;
            }
        } catch (NoSuchElementException elementException) {
            System.err.println("File improperly formed. Terminating");

            System.exit(1);
        } catch (IllegalStateException stateException) {
            System.err.println("Error reading from file. Terminating");

            System.exit(1);

        }

        CloseFile();

        return total;
    }

    //Transaction list
    public static void transactionList(String account, int _type) {
        String filename;

        if (_type == AppConstants.SAVING_ACCOUNT)
            filename = account + "-savings.txt";
        else
            filename = account + "-current.txt";

        if (hasFile(filename))
            OpenFileToRead(filename);


        try {
            while (input.hasNext()) {
                String date, transaction;
                double amount, balance;
                date = input.next();
                transaction = input.next();
                amount = input.nextDouble();
                balance = input.nextDouble();

                System.out.printf("%s %s %f %f %n", date, (transaction.equals("Lodge")) ? "Lodge" : "Withdraw", amount, balance);

            }
        } catch (NoSuchElementException elementException) {
            System.err.println("File improperly formed. Terminating");

            System.exit(1);
        } catch (IllegalStateException stateException) {
            System.err.println("Error reading from file. Terminating");

            System.exit(1);
        }
        CloseFile();
    }

    //Open the file to write
    private static void OpenFileToWrite(String fileToOpen) {

        try {
            FileWriter f = new FileWriter(fileToOpen, true);
            output = new Formatter(f);
        } catch (SecurityException securityException) {
            System.out.println("Write permission denied");
            System.exit(1);
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("Error opening file, Terminating");
            System.exit(1);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

    }

    private static void CloseFile() {

        if (output != null) {
            output.close();
        }


    }

    //Opne file to read
    public static void OpenFileToRead() {

        try {
            input = new Scanner(Paths.get(CustomerFile));
        } catch (IOException ioException) {
            System.err.println("Error opening file. Terminating");
            System.exit(1);
        }

    }

    private static void OpenFileToRead(String file) {

        try {
            input = new Scanner(Paths.get(file));
        } catch (IOException ioException) {
            System.err.println("Error opening " + file + ". Terminating");
            System.exit(1);
        }

    }

    //Date format to txt current and savings account
    public static String date() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date(System.currentTimeMillis());
        return formatter.format(date);
    }

    public static boolean hasCustomer(String firstName, String lastName, String accountNumber, String pin) {
        OpenFileToRead();

        try {
            while (input.hasNext()) {
                String firstName_, lastName_, email_, accountNumber_, pin_;
                firstName_ = input.next();
                lastName_ = input.next();
                email_ = input.next();
                accountNumber_ = input.next();
                pin_ = input.next();
                if (
                        firstName.equals(firstName_) &&
                                lastName.equals(lastName_) &&
                                accountNumber.equals(accountNumber_) &&
                                pin.equals(pin_)) {

                    return true;
                }

            }
        } catch (NoSuchElementException elementException) {
            System.err.println("File improperly formed. Terminating");

            System.exit(1);
        } catch (IllegalStateException stateException) {
            System.err.println("Error reading from file. Terminating");

            System.exit(1);

        }

        CloseFile();

        return false;
    }


}
