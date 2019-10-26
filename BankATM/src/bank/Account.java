/*
 * Athanasios Filippidis
 * aflpd@bu.edu
 * BU ID U95061883
 * */
package bank;

public abstract class Account {
    private static int accountUniqueID = 0;
    protected Float balanceInLocalCurrency;
    private int routingNumber;
    private int accountNumber;
    private boolean active;
    private Currency currency;
    protected Float openingCharge;
    protected Float closingCharge;

    public Account(Float balanceInLocalCurrency, int routingNumber, int accountNumber, boolean active, Currency currency, Float closingCharge, Float openingCharge) {
        this.balanceInLocalCurrency = balanceInLocalCurrency;
        this.routingNumber = routingNumber;
        this.accountNumber = accountNumber;
        this.active = active;
        this.currency = currency;
        this.closingCharge = closingCharge;
        this.openingCharge = openingCharge;
    }

    public Float getBalanceInLocalCurrency() {
        return balanceInLocalCurrency;
    }

    public void setBalanceInLocalCurrency(Float balanceInLocalCurrency) {
        this.balanceInLocalCurrency = balanceInLocalCurrency;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getRoutingNumber() {
        return routingNumber;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void changeCurrencyTo(String abbreviation){
        currency.setCurrency(abbreviation);
    }

    public void chargeOpeningCharge(){
        balanceInLocalCurrency -= openingCharge;
    }

    public void chargeClosingCharge(){
        balanceInLocalCurrency -= closingCharge;
    }

    public boolean withdrawAmount(Float amount){
        if (amount > balanceInLocalCurrency){
            //System.out.println("sigrinw" + amount + " " + balanceInLocalCurrency);
            return false;
        }
        balanceInLocalCurrency -= amount;
        return true;
    }

    public boolean deductAmountToBeTransferred(int amount){
        if (amount > balanceInLocalCurrency){
            return false;
        }
        balanceInLocalCurrency -= amount;
        return true;
    }

    public void depositAmountFromTransfer(Float amount){
        balanceInLocalCurrency += amount;
    }

    public void depositAmount(Float amount){
        balanceInLocalCurrency += amount;
    }

    public static int getNewAccountUniqueNumber(){
        return accountUniqueID++;
    }

    @Override
    public String toString() {
        return "Account number: " + accountNumber + " balance: " + balanceInLocalCurrency*Currency.getRate(currency.toString()) + currency.toString();
    }

    public Currency getCurrency() {
        return currency;
    }

    public void reimbursement(Float amount){
        balanceInLocalCurrency += amount;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}
