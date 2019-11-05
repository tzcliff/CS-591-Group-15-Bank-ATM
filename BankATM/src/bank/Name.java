/*
 * Athanasios Filippidis
 * aflpd@bu.edu
 * BU ID U95061883
 * */
package bank;

public class Name {
    private String firstName;
    private String lastName;

    public Name(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
