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
            ResultSet rs=stmt.executeQuery("select * from account");
            while(rs.next())
                System.out.println(rs.getInt(1));
        }catch(Exception e){ System.out.println(e);}
    }

    public void addPerson(CustomerAccount customerAccount){
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("INSERT INTO ");
        }
        catch(Exception e){ System.out.println(e);}
    }
    public void addCheckingAccount(CheckingAccount checkingAccount){

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

}

