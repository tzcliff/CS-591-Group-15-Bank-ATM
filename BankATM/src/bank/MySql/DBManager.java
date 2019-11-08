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
                "\'"+ checkingAccount.isActive() +"\', \'"+ checkingAccount.getOpeningCharge() +"\', \'"+ checkingAccount.getClosingCharge() +"\', \'" + checkingAccount.getTransactionFee() + "\', \'"+ checkingAccount.getWithdrawalFee() +"\', \'" + "C" +"\');";
        sqlExecute(sql);
    }
    public void addSavingAccount(SavingsAccount savingsAccount){

    }

//    public void addSecurityAccount(SavingsAccount savingsAccount){
//
//    }

    public void updatePerson(CustomerAccount customerAccount){

    }
    public void updateCheckingAccount(CheckingAccount checkingAccount){

    }
    public void updateSavingAccount(SavingsAccount savingsAccount){

    }


    public void deletePerson(CustomerAccount customerAccount){

    }
    public void deleteCheckingAccount(CheckingAccount checkingAccount){

    }
    public void deleteSavingAccount(SavingsAccount savingsAccount){

    }

    public void sqlExecute(String sql){
        try {
            Statement stmt = con.createStatement();
            stmt.execute(sql);
        }
        catch(Exception e){ System.out.println(e);}
    }

}

