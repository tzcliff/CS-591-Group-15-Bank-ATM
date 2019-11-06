/*
 * Athanasios Filippidis
 * aflpd@bu.edu
 * BU ID U95061883
 * */
package bank;

import java.util.ArrayList;

/*
There are two not-so-clear points here. The first is collateral. The way I have implemented this a customer either has collateral or does not.
If he/she has that means that he can have any kind of loans he/she asks. If not, well, he/she does not. At any time the manager can change
this trust indicator of a client. The second kind of blurry part is the cancelLastTransferWithdrawal. As stated,
This method was used as a helper method for a transfer. A transfer consists from two parts, the withdraw and the deposit part.
However if the withdraw part succeeds and the deposit part for some reason fails this is the method that returns the
system to the previous status like neither of the parts ever happened
 */

public class CustomerAccount {
    private Person person;
    private ArrayList<CheckingAccount> checkingAccounts;
    private ArrayList<SavingsAccount> savingsAccounts;
    private ArrayList<Loan> loans;
    private ArrayList<Transaction> transactions;
    private ArrayList<SecurityAccount> securityAccounts;
    private boolean collateral;

    public CustomerAccount(Person person, boolean collateral) {
        this.person = person;
        this.collateral = collateral;
        this.checkingAccounts = new ArrayList<CheckingAccount>();
        this.savingsAccounts = new ArrayList<SavingsAccount>();
        this.transactions = new ArrayList<Transaction>();
        this.loans= new ArrayList<Loan>();
        this.securityAccounts = new ArrayList<SecurityAccount>();
    }

    public CustomerAccount(Person person) {
        this(person, true);
    }

    @Override
    public String toString() {
        return person.toString();
    }

    public void addNewCheckingAccount(){
        Currency currency = new Currency("USD");
        checkingAccounts.add(new CheckingAccount(0.0f, 0, CheckingAccount.getNewAccountUniqueNumber(), true, currency,
                0.0f, 0.0f, 0.0f,0.0f));
    }

    public void addNewSavingsAccount(){
        Currency currency = new Currency("USD");
        savingsAccounts.add(new SavingsAccount(0.0f, 0, SavingsAccount.getNewAccountUniqueNumber(), true, currency,
                0.0f, 0.0f, 0.0f));
    }

    public boolean addNewSecurityAccount(Float securityBalanceThreshold){
        Currency currency = new Currency("USD");
        // If the user is eligible for a new security account open it and return true, otherwise false
        if (eligibleToOpenSecurityAccount(securityBalanceThreshold)){
            securityAccounts.add(new SecurityAccount(0f, 0, SecurityAccount.getNewAccountUniqueNumber(), true, currency,
                    0f, 0f));
        }
        return false;
    }

    public boolean addNewLoan(Float amount, Currency currency){
        if (collateral){
            //Currency currency = new Currency("USD");
            loans.add(new Loan(currency, 0.0f, amount));
            return true;
        }
        return false;
    }

    // Method that tries to find in this customer the account specified by receiverAccountNumber and receiverRoutingNumber to perform a deposit
    // If it is found it adds this transaction to this users transactions and returns true, if the account is not found
    // it returns false
    public boolean depositAmount(Float amount, Currency currency, int receiverAccountNumber, int receiverRoutingNumber){
        for (CheckingAccount checkingAccount :
                checkingAccounts) {
            if (checkingAccount.getAccountNumber() == receiverAccountNumber && checkingAccount.getRoutingNumber() == receiverRoutingNumber){
                checkingAccount.depositAmount(amount);
                transactions.add(new Deposit(amount*Currency.getRate(currency.toString()), currency, receiverAccountNumber, receiverRoutingNumber));
                return true;
            }

        }

        for (SavingsAccount savingsAccount :
                savingsAccounts) {
            if (savingsAccount.getAccountNumber() == receiverAccountNumber && savingsAccount.getRoutingNumber() == receiverRoutingNumber){
                savingsAccount.depositAmount(amount);
                transactions.add(new Deposit(amount*Currency.getRate(currency.toString()), currency, receiverAccountNumber, receiverRoutingNumber));
                return true;
            }

        }
        return false;
    }

    // Method that tries to find in this customer the account specified by receiverAccountNumber and receiverRoutingNumber to perform a transfer deposit
    // If it is found it adds this transaction to this users transactions and returns true, if the account is not found
    // it returns false
    public boolean depositTransferAmount(Float amount, Currency currency, int senderAccountNumber, int senderRoutingNumber, int receiverAccountNumber, int receiverRoutingNumber){
        for (CheckingAccount checkingAccount :
                checkingAccounts) {
            if (checkingAccount.getAccountNumber() == receiverAccountNumber && checkingAccount.getRoutingNumber() == receiverRoutingNumber){
                checkingAccount.depositAmountFromTransfer(amount);
                transactions.add(new Transfer(amount*Currency.getRate(currency.toString()), currency, senderAccountNumber, senderRoutingNumber, receiverAccountNumber, receiverRoutingNumber));
                return true;
            }

        }

        for (SavingsAccount savingsAccount :
                savingsAccounts) {
            if (savingsAccount.getAccountNumber() == receiverAccountNumber && savingsAccount.getRoutingNumber() == receiverRoutingNumber){
                savingsAccount.depositAmountFromTransfer(amount);
                transactions.add(new Transfer(amount*Currency.getRate(currency.toString()), currency, senderAccountNumber, senderRoutingNumber, receiverAccountNumber, receiverRoutingNumber));
                return true;
            }

        }
        return false;
    }

    // Method that tries to find in this customer the account specified by receiverAccountNumber and receiverRoutingNumber to perform a withdrawal
    // If it is found it adds this transaction to this users transactions and returns true, if the account is not found
    // it returns false
    public boolean withdrawAmount(Float amount, Currency currency, int senderAccountNumber, int senderRoutingNumber){
        for (CheckingAccount checkingAccount :
                checkingAccounts) {
            if (checkingAccount.getAccountNumber() == senderAccountNumber && checkingAccount.getRoutingNumber() == senderRoutingNumber){
                if (checkingAccount.withdrawAmount(amount)){
                    transactions.add(new Withdrawal(amount*Currency.getRate(currency.toString()), currency, senderAccountNumber, senderRoutingNumber));
                    return true;
                }else {
                    return false;
                }
            }

        }

        for (SavingsAccount savingsAccount :
                savingsAccounts) {
            if (savingsAccount.getAccountNumber() == senderAccountNumber && savingsAccount.getRoutingNumber() == senderRoutingNumber){
                if (savingsAccount.withdrawAmount(amount)){
                    transactions.add(new Withdrawal(amount*Currency.getRate(currency.toString()), currency, senderAccountNumber, senderRoutingNumber));
                    return true;
                }else {
                    return false;
                }
            }

        }
        return false;
    }

    // Method that tries to find in this customer the account specified by receiverAccountNumber and receiverRoutingNumber to perform a transfer withdrawal
    // If it is found it adds this transaction to this users transactions and returns true, if the account is not found
    // it returns false
    public boolean withdrawTransferAmount(Float amount, Currency currency, int senderAccountNumber, int senderRoutingNumber, int receiverAccountNumber, int receiverRoutingNumber){
        for (CheckingAccount checkingAccount :
                checkingAccounts) {
            if (checkingAccount.getAccountNumber() == senderAccountNumber && checkingAccount.getRoutingNumber() == senderRoutingNumber){
                if (checkingAccount.withdrawAmount(amount)){
                    transactions.add(new Transfer(amount*Currency.getRate(currency.toString()), currency, senderAccountNumber, senderRoutingNumber, receiverAccountNumber, receiverRoutingNumber));
                    return true;
                }else{
                    return false;
                }
            }

        }

        for (SavingsAccount savingsAccount :
                savingsAccounts) {
            if (savingsAccount.getAccountNumber() == senderAccountNumber && savingsAccount.getRoutingNumber() == senderRoutingNumber){
                if (savingsAccount.withdrawAmount(amount)){
                    transactions.add(new Transfer(amount*Currency.getRate(currency.toString()), currency, senderAccountNumber, senderRoutingNumber, receiverAccountNumber, receiverRoutingNumber));
                    return true;
                }else{
                    return false;
                }
            }

        }
        return false;
    }

    // Method that is called if the second part of a transfer fails. The purpose of this method is to give back the money to the account
    // from which we made the transfer withdrawal and to delete the transaction from its list of transactions. I know that this may be confusing
    // feel free to contact aflpd@bu.edu for more details
    public void cancelLastTransferWithdrawal(){
        Transaction lastTransaction = transactions.get(transactions.size() - 1);
        int lastTransAccN = lastTransaction.getSenderAccountNumber();
        int lastTransRoutN = lastTransaction.getSenderRoutingNumber();
        Float lastTransAmount = lastTransaction.getAmount()*Currency.getRate(lastTransaction.getCurrency().toString());

        boolean transferCanceled = false;

        for (CheckingAccount checkingAccount :
                checkingAccounts) {
            if (checkingAccount.getAccountNumber() == lastTransAccN && checkingAccount.getRoutingNumber() == lastTransRoutN){
                checkingAccount.reimbursement(lastTransAmount);
                transactions.remove(transactions.size() - 1);
                transferCanceled = true;
            }

        }

        if (!transferCanceled){
            for (SavingsAccount savingsAccount :
                    savingsAccounts) {
                if (savingsAccount.getAccountNumber() == lastTransAccN && savingsAccount.getRoutingNumber() == lastTransRoutN){
                    savingsAccount.reimbursement(lastTransAmount);
                    transactions.remove(transactions.size() - 1);
                    break;
                }

            }
        }

    }

    public Person getPerson() {
        return person;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public SavingsAccount getLastSavingsAccount(){
        if (savingsAccounts.size() > 0){
            return savingsAccounts.get(savingsAccounts.size() - 1);
        }
        return null;
    }

    public CheckingAccount getLastCheckingAccount(){
        if (checkingAccounts.size() > 0){
            return checkingAccounts.get(checkingAccounts.size() - 1);
        }
        return null;
    }

    public boolean isCollateral() {
        return collateral;
    }

    public ArrayList<Loan> getLoans() {
        return loans;
    }

    public ArrayList<CheckingAccount> getCheckingAccounts() {
        return checkingAccounts;
    }

    public ArrayList<SavingsAccount> getSavingsAccounts() {
        return savingsAccounts;
    }

    // Method that checks if the total amount of money the user has is enough to trust him with a security account.
    // To determine that we sum up all the money that he has over all of his accounts, we deduct from that amount
    // any loans he owes and if the final amount is bigger or equal to the securityBalanceThreshold that the manager requires
    // then we return true, otherwise false
    public boolean eligibleToOpenSecurityAccount(Float securityBalanceThreshold){
        Float totalBalance = 0f;
        for (CheckingAccount checkingAcc :
                checkingAccounts) {
            totalBalance += checkingAcc.getBalanceInLocalCurrency();
        }

        for (SavingsAccount savingAcc :
                savingsAccounts) {
            totalBalance += savingAcc.getBalanceInLocalCurrency();
        }

        for (Loan loan :
                loans) {
            totalBalance -= loan.getDebtInLocalCurrency();
        }

        if (totalBalance >= securityBalanceThreshold){
            return true;
        }
        return false;
    }
}
