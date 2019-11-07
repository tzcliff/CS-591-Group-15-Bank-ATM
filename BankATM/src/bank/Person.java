/*
 * Athanasios Filippidis
 * aflpd@bu.edu
 * BU ID U95061883
 * */
package bank;

public class Person {
    private static int person_id = 0;

    private Name name;

    public Person(String firstName, String lastName) {
        this.name = new Name(firstName, lastName);
    }

    public static int getNewPersonID(){
        return person_id++;
    }

    @Override
    public String toString() {
        return name.toString();
    }

	public Name getName() {
		return name;
	}

	
}
