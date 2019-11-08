package bank.MySql;

import bank.CustomerAccount;

import bank.*;
import java.sql.*;
public class DBManager {

    Connection con;

    public DBManager(){
        connect();
    }
    public void connect(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/bank_atm","admin","admin");
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select * from person");
            while(rs.next())
                System.out.println(rs.getString(2));
        }catch(Exception e){ System.out.println(e);}
    }

    public void addPerson(CustomerAccount customerAccount){
        String sql = "INSERT INTO bank_atm.person (first_name, last_name) VALUES (\'"+ customerAccount.getPerson().getName().getFirstName() + "\', \'"+ customerAccount.getPerson().getName().getFirstName()+ "\');";
        sqlExecute(sql);
    }
    public void addCheckingAccount(CheckingAccount checkingAccount){
        String sql = "INSERT INTO bank_atm.account (balance, routing_num, acc_num, active, open_fee, close_fee, transaction_fee, withdrrawal_fee, type) VALUES (\'" + checkingAccount.getBalanceInLocalCurrency() + "\', \'" + checkingAccount.getRoutingNumber() + "\', \'" + checkingAccount.getAccountNumber() + "\', " +
                ""+ checkingAccount.isActive() +", \'"+ checkingAccount.getOpeningCharge() +"\', \'"+ checkingAccount.getClosingCharge() +"\', \'" + checkingAccount.getTransactionFee() + "\', \'"+ checkingAccount.getWithdrawalFee() +"\', \'" + "C" +"\');";
        sqlExecute(sql);
    }
    public void addSavingAccount(SavingsAccount savingsAccount){
        String sql = "INSERT INTO bank_atm.account (balance, routing_num, acc_num, active, open_fee, close_fee, interest, type) VALUES (\'" + savingsAccount.getBalanceInLocalCurrency() + "\', \'" + savingsAccount.getRoutingNumber() + "\', \'" + savingsAccount.getAccountNumber() + "\', " +
                ""+ savingsAccount.isActive() +", \'"+ savingsAccount.getOpeningCharge() +"\', \'"+ savingsAccount.getClosingCharge() +"\', \'" + savingsAccount.getInterest() + "\', \'" + "S" +"\');";
        sqlExecute(sql);
    }

    public void addBoughtStock(BoughtStock boughtStock, SecurityAccount securityAccount){
        String sql = "INSERT INTO bank_atm.bought_stock (share_amount, worth, account_id) VALUES (\'"+ boughtStock.getAmountOfStocks() +"\', \'" + boughtStock.getTotalAmountSpentOnBuying() + "\', \'"+ securityAccount.getAccountNumber()+"\');";

        sqlExecute(sql);
    }

    public void addTransaction(Transaction transaction){
        String sql = "INSERT INTO bank_atm.transaction (sender_acc_num, sender_routing_num, rec_acc_num, rec_routing_num) VALUES (\'" +transaction.getSenderAccountNumber()+ "\', \'"+ transaction.getSenderRoutingNumber()+"\', \'" + transaction.getReceiverAccountNumber()+ "\', \'"+transaction.getReceiverRoutingNumber()+"\');";
        sqlExecute(sql);
    }

    public void addLoan(Loan loan, CustomerAccount customerAccount){
        String sql = " INSERT INTO bank_atm.loan (initial_amount, debt, person_name, interest) VALUES (\'"+loan.getInitialAmountInLocalCurrency()+"\', \'"+loan.getDebtInLocalCurrency()+"\', \'"+customerAccount.getPerson().getName().getFirstName()+"\', \'"+loan.getInterest()+"\');";
        sqlExecute(sql);
    }





    public void sqlExecute(String sql){
        try {
            Statement stmt = con.createStatement();
            stmt.execute(sql);
        }
        catch(Exception e){ System.out.println(e);}
    }

}

