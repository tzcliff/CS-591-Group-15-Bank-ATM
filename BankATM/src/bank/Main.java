/*
 * Athanasios Filippidis
 * aflpd@bu.edu
 * BU ID U95061883
 * */
package bank;

public class Main {

    public static void main(String[] args) {
        Person manager = new Person("Christine", "Papadakis");
        BankManagerAccount managerAccount = new BankManagerAccount(manager);
        Bank bank = new Bank(managerAccount);
    }
}
