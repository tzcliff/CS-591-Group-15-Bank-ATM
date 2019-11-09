package bank.MySql;

import bank.CustomerAccount;

import bank.*;

import java.nio.charset.CharsetEncoder;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
        String sql = "INSERT INTO bank_atm.person (name) VALUES (\'"+ customerAccount.getPerson().getName().getFirstName() + " "+ customerAccount.getPerson().getName().getFirstName()+ "\');";
        sqlExecute(sql);
    }
    public void addCheckingAccount(CheckingAccount checkingAccount){
        String sql = "INSERT INTO bank_atm.account (balance, routing_num, acc_num, active, open_fee, close_fee, transaction_fee, withdrrawal_fee, type, person_name) VALUES (\'" + checkingAccount.getBalanceInLocalCurrency() + "\', \'" + checkingAccount.getRoutingNumber() + "\', \'" + checkingAccount.getAccountNumber() + "\', " +
                ""+ checkingAccount.isActive() +", \'"+ checkingAccount.getOpeningCharge() +"\', \'"+ checkingAccount.getClosingCharge() +"\', \'" + checkingAccount.getTransferFee() + "\', \'"+ checkingAccount.getWithdrawalFee() +"\', \'" + "C" +"\');";
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
        String sql = " INSERT INTO bank_atm.loan (initial_amount, debt, name, interest) VALUES (\'"+loan.getInitialAmountInLocalCurrency()+"\', \'"+loan.getDebtInLocalCurrency()+"\', \'"+customerAccount.getPerson().getName().getFirstName()+ " " +  customerAccount.getPerson().getName().getLastName()+"\', \'"+loan.getInterest()+"\');";
        sqlExecute(sql);
    }

    public void addStock(Stock stock){

        String sql = "INSERT INTO bank_atm.stock (price, total_shares, avai_shares) VALUES (\' " + stock.getCurrentPrice()+ "\', \'"+ stock.getTotalShares()+"\', \'"+ stock.getCurrentlyAvailableShares()+"\');";
        sqlExecute(sql);

    }


    public List<CheckingAccount> readCheckingkAccout(){
        List<CheckingAccount> list = new ArrayList<>();


        try {
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select * from account WHERE type = \'C\'");
            CheckingAccount temp;
            while(rs.next()) {

                temp = new CheckingAccount(rs.getFloat(1), rs.getInt("routing_num"), rs.getInt("acc_num"), rs.getBoolean("active"), new Currency("USD")
                        , rs.getFloat("close_fee"), rs.getFloat("open_fee"), rs.getFloat("transaction_fee"), rs.getFloat("withdrawal_fee"));
                list.add(temp);
            }
        }
        catch(Exception e){ System.out.println(e);}
        return list;
    }


    public List<SavingsAccount> readSavingAccount(){
        List<SavingsAccount> list = new ArrayList<>();


        try {
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select * from account WHERE type = \'S\'");
            SavingsAccount temp;
            while(rs.next()) {

                temp = new SavingsAccount(rs.getFloat(1), rs.getInt("routing_num"), rs.getInt("acc_num"), rs.getBoolean("active"), new Currency("USD")
                        , rs.getFloat("close_fee"), rs.getFloat("open_fee"), rs.getFloat("interest"));
                list.add(temp);
            }
        }
        catch(Exception e){ System.out.println(e);}
        return list;
    }

    public void updateCheckingAccount(CheckingAccount checkingAccount){
        String sql = "UPDATE bank_atm.account SET balance = \'" + checkingAccount.getBalanceInLocalCurrency() +  "\', active = "+ checkingAccount.isActive() + " , open_fee = \'" + checkingAccount.getOpeningCharge() +"\', close_fee = \'" + checkingAccount.getClosingCharge() +"\', transaction_fee = \'" + checkingAccount.getTransferFee() + "\', " +
                "withdrrawal_fee = \'"+ checkingAccount.getWithdrawalFee() +"\' WHERE (acc_num = \' "+ checkingAccount.getAccountNumber()+" \' AND routing_num = \'"+ checkingAccount.getAccountNumber()+ " \');\n";
        sqlExecute(sql);
    }

    public void updateSavingAccount(SavingsAccount savingsAccount){
        String sql = "UPDATE bank_atm.account SET balance = \'" + savingsAccount.getBalanceInLocalCurrency() +  "\', active = "+ savingsAccount.isActive() + " , open_fee = \'" + savingsAccount.getOpeningCharge() +"\', close_fee = \'" + savingsAccount.getClosingCharge() +"\'," +
                "interest = \'"+ savingsAccount.getInterest() +"\' WHERE (acc_num = \' "+ savingsAccount.getAccountNumber()+" \' AND routing_num = \'"+ savingsAccount.getAccountNumber()+ " \');\n";
        sqlExecute(sql);
    }

    public void updateAccount(Account account){
        String sql = "UPDATE bank_atm.account SET balance = \'" + account.getBalanceInLocalCurrency() +  "\', active = "+ account.isActive() + " , open_fee = \'" + account.getOpeningCharge() +"\', close_fee = \'" + account.getClosingCharge() +"\'," +
                "\' WHERE (acc_num = \' "+ account.getAccountNumber()+" \' AND routing_num = \'"+ account.getAccountNumber()+ " \');\n";
        sqlExecute(sql);
    }

    public void updateStock(Stock stock){
        String sql = "UPDATE bank_atm.stock SET price = \'" +stock.getCurrentPrice()+ "\', total_shares = \'"+ stock.getTotalShares()+"\', avai_shares = \'"+stock.getCurrentlyAvailableShares()+"\', WHERE (name = \'"+stock.getName()+"\');";

        sqlExecute(sql);
    }

    public void updateBoughtStock(BoughtStock boughtStock, SecurityAccount account){


        String sql = "UPDATE bank_atm.bought_stock SET share_amount = \'"+ boughtStock.getAmountOfStocks() +"\', worth = \'" + boughtStock.getTotalAmountSpentOnBuying()+ "\' WHERE (stock_name = \'"+boughtStock.getStock().getName()+"\' AND account_id = \'" + account.getAccountNumber()+"\');";
        sqlExecute(sql);

    }

    public void deleteBoughtStock(BoughtStock boughtStock, SecurityAccount account){

    }






    public void sqlExecute(String sql){
        try {
            Statement stmt = con.createStatement();
            stmt.execute(sql);
        }
        catch(Exception e){ System.out.println(e);}
    }
}

