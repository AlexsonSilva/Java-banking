//21643 Alexson O Silva
//21643@student.dorset-colle.ie

package com.company;

import java.util.Scanner;

public class MenuBuilder {
    private static int startMenu() {

        int selection;
        Scanner input = new Scanner(System.in);
        /***************************************************/

        String MENU = "\nPlease select one of the following options:\n" +
                "1: Bank Employee.\n" +
                "2: Customer.\n" +
                "3: Exit program.\n\n" +
                "> ";
        System.out.println(MENU);

        selection = input.nextInt();
        return selection;

    }

    private static int customerMenu() {

        int selection;
        Scanner input = new Scanner(System.in);

        System.out.println("-------------------------\n");
        System.out.println("1 - Lodge ");
        System.out.println("2 - Withdraw");
        System.out.println("3 - List transactions");
        System.out.println("4 - Quit");

        selection = input.nextInt();
        return selection;

    }

    private static String getCustomerPin() {
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Enter your First Name");
        String firstName = myObj.nextLine();
        System.out.println("First Name is: " + firstName);

        System.out.println("Enter your Last Name");
        String lastName = myObj.nextLine();
        System.out.println("Last Name is: " + lastName);

        return firstName + "" + lastName;
    }

    private static Customer AddCustomerForm() {
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Enter First Name");

        String firstName = myObj.nextLine();  // Read user input
        System.out.println("FirstName  is: " + firstName);  // Output user input

        System.out.println("Enter Last Name");
        String lastName = myObj.nextLine();  // Read user input
        System.out.println("LastName  is: " + lastName);  // Output user input

        System.out.println("Enter email");
        String email = myObj.nextLine();  // Read user input
        System.out.println("Email  is: " + email);  // Output user input

        Customer customer = new Customer(firstName, lastName, email);
        FileManager.AddCustomerToFile(customer);

        return customer;
    }

    private static int transactionMenu() {

        int selection;
        Scanner input = new Scanner(System.in);

        System.out.println("-------------------------\n");
        System.out.println("1 - Lodge Money");
        System.out.println("2 - Withdraw Money");


        selection = input.nextInt();
        return selection;

    }

    private static BankingTransaction CreateTransactionLodgeForm() {

        Scanner myObj = new Scanner(System.in);

        System.out.println("Enter the account:");
        String account = myObj.nextLine();

        System.out.println("Enter the amount:");
        double amount = myObj.nextDouble();

        System.out.println("Enter the type account (1 - Current / 2 - Savings)");
        int typeAccount = myObj.nextInt();

        Customer customer = FileManager.getCustomer(account);

        if (customer == null) {
            System.out.println("Customer account: " + account + " doesn't exist");
            return null;
        }

        System.out.println("Transaction lodge - account: " + account + " amount: " + amount + " typeAccount: " + typeAccount + " ");


        BankingTransactionLodge btl = new BankingTransactionLodge(customer, amount, typeAccount);


        return btl;

    }

    private static BankingTransaction CreateTransactionWithdrawForm() {
        Scanner myObj = new Scanner(System.in);

        System.out.println("Enter the account:");
        String account = myObj.nextLine();

        System.out.println("Enter the amount:");

        double amount = myObj.nextDouble();

        System.out.println("Enter the type account (1 - Current / 2 - Savings)");
        int typeAccount = myObj.nextInt();

        System.out.println("Transaction lodge amount: " + amount + " typeAccount: " + typeAccount + " ");

        Customer customer = FileManager.getCustomer(account);

        if (customer == null) {
            System.out.println("Customer account: " + account + " doesn't exist");
            return null;
        }

        BankingTransactionWithdraw btl = new BankingTransactionWithdraw(customer, amount, typeAccount);

        return btl;
    }

    private static void DeleteCustomer() {


    }

    private static int createTransactionListForm(Customer customer) {

        System.out.println("Customer Transaction list");

        Scanner myObj = new Scanner(System.in);

        System.out.println("Enter the type account (1 - Current / 2 - Savings)");
        int typeAccount = myObj.nextInt();

        return typeAccount;

    }


    private static int bankingMenu() {

        int selection;
        Scanner input = new Scanner(System.in);

        System.out.println("-------------------------\n");
        System.out.println("1 - Add Customer");
        System.out.println("2 - Delete Customer");
        System.out.println("3 - Create Transaction");
        System.out.println("4 - List Customers");
        System.out.println("5 - Quit");

        selection = input.nextInt();
        return selection;

    }

    private static String getBankEmployeePin() {
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Enter PIN");

        String pin = myObj.nextLine();  // Read user input
        System.out.println("PIN is: " + pin);  // Output user input

        return pin;

    }

    private static String[] getCustomerCredentials() {

        String customer[] = new String[4];
        Scanner myObj = new Scanner(System.in);

        System.out.println("Enter first name");
        customer[0] = myObj.nextLine(); //first name

        System.out.println("Enter last name");
        customer[1] = myObj.nextLine(); //last name

        System.out.println("Enter bank account");
        customer[2] = myObj.nextLine(); //account name

        System.out.println("Enter PIN");
        customer[3] = myObj.nextLine(); //pin name

        return customer;
    }


    public static void Run() {

        int userChoice;

        do {
            userChoice = MenuBuilder.startMenu();
            switch (userChoice) {

                case AppConstants.EMPLOYEE:

                    // Get the banking Pin from employee
                    String pin = getBankEmployeePin();
                    SecurityBanking sb = new SecurityBanking();
                    boolean isAuthenticated = sb.login(pin);

                    // verify if employee is authenticated

                    if (isAuthenticated) {

                        int bankEmployeeAction;

                        do {
                            bankEmployeeAction = MenuBuilder.bankingMenu();

                            switch (bankEmployeeAction) {

                                case AppConstants.CUSTOMER_CREATE:
                                    Customer newCustomer = AddCustomerForm();
                                    BankingAction.AddCustomer(newCustomer);
                                    break;
                                case AppConstants.CUSTOMER_DELETE:
                                    break;
                                case AppConstants.TRANSACTION_CREATE:
                                    int transactionChoice = 0;


                                    do {

                                        transactionChoice = MenuBuilder.transactionMenu();

                                        switch (transactionChoice) {
                                            case AppConstants.TRANSACTION_LODGE:

                                                BankingTransaction btl = CreateTransactionLodgeForm();
                                                BankingAction.CreateTransaction(btl);
                                                MenuBuilder.bankingMenu();
                                                break;
                                            case AppConstants.TRANSACTION_WITHDRAW:
                                                BankingTransaction btw = CreateTransactionWithdrawForm();
                                                BankingAction.CreateTransaction(btw);
                                                MenuBuilder.bankingMenu();
                                                break;
                                            default:
                                                System.out.println("Invalid transaction choice!");
                                        }
                                    }
                                    while (transactionChoice != 2);
                                    break;
                                case AppConstants.CUSTOMER_LIST:
                                    BankingAction.listCustomerBalance();
                                    break; // AppConstants.CUSTOMER_LIST
                                case AppConstants.QUIT:
                                    break;
                                default:
                                    System.out.println("Invalid transaction choice");
                            }

                        }
                        while (bankEmployeeAction != 5);
                    } else {
                        System.out.println("Authentication error...");
                        MenuBuilder.bankingMenu();
                        return;
                    }
                    break;

                case AppConstants.CUSTOMER:

                    SecurityCustomer sc = new SecurityCustomer();
                    String[] credentials = getCustomerCredentials();

                    boolean isCustomerAuthenticated = sc.getCredentials(credentials[0], credentials[1], credentials[2], credentials[3]);

                    if (isCustomerAuthenticated) {
                        Customer c = FileManager.getCustomer(credentials[2]);

                        int customerAction;

                        do {
                            customerAction = MenuBuilder.customerMenu();

                            switch (customerAction) {
                                case AppConstants.TRANSACTION_LODGE:
                                    BankingTransaction btl = CreateTransactionLodgeForm();
                                    BankingAction.CreateTransaction(btl);
                                    break;
                                case AppConstants.TRANSACTION_WITHDRAW:
                                    BankingTransaction btw = CreateTransactionWithdrawForm();
                                    BankingAction.CreateTransaction(btw);
                                    break;
                                case AppConstants.TRANSACTION_LIST:
                                    int type = createTransactionListForm(c);
                                    BankingAction.ListTransactionHistory(c.getAccount(), type);
                                    break;
                                case 4:
                                    return;
                                default:
                                    System.out.println("Invalid customer action!");
                            }
                        }
                        while (customerAction != 5);

                    } else {
                        System.out.println("error....");
                        return;
                    }
                    break;
                case 3:
                    System.out.println("Exiting program...");
                    return;
                case 4:
                    return;
            }

        }
        while (userChoice != 4);
    }
}
