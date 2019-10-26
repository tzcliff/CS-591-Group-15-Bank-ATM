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


}
