/*
 * Athanasios Filippidis
 * aflpd@bu.edu
 * BU ID U95061883
 * */
package bank;

public class CheckingAccount extends Account {
    private Float transactionFee;
    private Float withdrawalFee;

    public CheckingAccount(Float amountInLocalCurrency, int routingNumber, int accountNumber, boolean active, Currency currency,
                           Float closingCharge, Float openingCharge, Float transactionFee, Float withdrawalFee) {
        super(amountInLocalCurrency, routingNumber, accountNumber, active, currency, closingCharge, openingCharge);
        this.transactionFee = transactionFee;
        this.withdrawalFee = withdrawalFee;
    }

    // If the parent class reassures us that the money asked to withdraw exist, if the account is a checking account we have
    // to ensure that there exist enough money to deduct the withdraw fee as well, otherwise return that the transaction could
    // not be performed
    @Override
    public boolean withdrawAmount(Float amount){
        if (super.withdrawAmount(amount)){
            if (withdrawalFee > balanceInLocalCurrency){
                balanceInLocalCurrency += amount;
                return false;
            }
            balanceInLocalCurrency -= withdrawalFee;
            return true;
        }
        return false;
    }

    // If the parent class reassures us that the money asked to deduct for a transfer exist, if the account is a checking account we have
    // to ensure that there exist enough money to deduct the transfer fee as well, otherwise return that the transaction could
    // not be performed
    @Override
    public boolean deductAmountToBeTransferred(int amount){
        if (super.deductAmountToBeTransferred(amount)){
            if (transactionFee > balanceInLocalCurrency){
                balanceInLocalCurrency += amount;
                return false;
            }
            balanceInLocalCurrency -= transactionFee;
            return true;
        }
        return false;
    }

    public Float getTransactionFee() {
        return transactionFee;
    }

    public void setTransactionFee(Float transactionFee) {
        this.transactionFee = transactionFee;
    }

    public Float getWithdrawalFee() {
        return withdrawalFee;
    }

    public void setWithdrawalFee(Float withdrawalFee) {
        this.withdrawalFee = withdrawalFee;
    }
}
