/*
 * Athanasios Filippidis
 * aflpd@bu.edu
 * BU ID U95061883
 * */
package bank;

import bank.MySql.DBManager;

import javax.swing.JFrame;

public class Main {
	
	

    public static void main(String[] args) {
        Person manager = new Person("Christine", "Papadakis");
        BankManagerAccount managerAccount = new BankManagerAccount(manager);
        Bank bank = new Bank(managerAccount);

        DBManager dbManager = new DBManager();
        dbManager.connect();
        CustomerAccount dummy = new CustomerAccount(new Person("Joe", "Biden") , false);
        //dbManager.addPerson(dummy);
        Data.setBank(bank);
        
        //WindowsBuilder w = new WindowsBuilder();
       
        
    }
    
   
}

// adding the my code
