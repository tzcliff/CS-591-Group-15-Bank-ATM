/*
 * Athanasios Filippidis
 * aflpd@bu.edu
 * BU ID U95061883
 * */
package bank;

import javax.swing.JFrame;
import bank.MySql.*;

public class Main {
	
	

    public static void main(String[] args) {
        Person manager = new Person("Christine", "Papadakis");
        BankManagerAccount managerAccount = new BankManagerAccount(manager);
        Bank bank = new Bank(managerAccount);

        DBManager dbManager = new DBManager();
        dbManager.connect();
        CustomerAccount dummy = new CustomerAccount(new Person("Joe", "Biden") , false);

        Data.setBank(bank);
        
        WindowsBuilder w = new WindowsBuilder();
       
        
    }
    
   
}

// adding the my code
