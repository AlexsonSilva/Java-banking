//21643 Alexson O Silva
//21643@student.dorset-colle.ie

package com.company;

public class BankingTransactionLodge extends BankingTransaction {
    public BankingTransactionLodge(Customer _customer, double _amount, int _accountType) {
        customer = _customer;
        amount = _amount;
        typeAccount = _accountType;
        typeTransaction = "Lodge";
    }
}
