package com.company;

public class BankingAction {
    public static int countFName = 0;
    public static int countLName = 0;

    public static void AddCustomer(Customer customer) {
        System.out.println("Creating " + customer.firstName + " " + customer.lastName);

    }

    public static void DeleteCustomer() {

    }

    public static void CreateTransaction(BankingTransaction transaction) {
        if (transaction.customer != null) {
            System.out.println(" value: " + transaction.amount + " account: " + transaction.customer.getAccount() + " type: " + transaction.typeTransaction + " ");

            FileManager.AddTransactionToFile(transaction);
        }
    }

    public static void ListCustomers() {
        FileManager.ListCustomers();

    }

    public static void listCustomerBalance() {
        FileManager.ListCustomersBalance();
    }

    public static void ListTransactionHistory(String account, int type) {
        FileManager.transactionList(account, type);

    }

}
