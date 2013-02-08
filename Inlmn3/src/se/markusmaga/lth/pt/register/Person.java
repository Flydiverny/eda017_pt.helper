package se.markusmaga.lth.pt.register;

import java.lang.Comparable;

public class Person implements Comparable<Person> {
	private String name;
	private String nbr;

	/**
	 * Creates a new Person with name and number.
	 * @param name - Name of person
	 * @param nbr - Number to person
	 */
	public Person(String name, String nbr) {
		this.name = name;
		this.nbr = nbr;
	}
	
	/**
	 * Returns name of person;
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Returns number for person
	 */
	public String getNumber() {
		return this.nbr;
	}
	
	/**
	 * Prints name and number
	 */
	public String toString() {
		return name + ": " + nbr;
	}
	
	/**
	 * Compares person to another person using name and number if identical names.
	 */
	@Override
	public int compareTo(Person o) {
		int name = this.getName().compareToIgnoreCase(o.getName());
        return name == 0 ? this.getNumber().compareToIgnoreCase(o.getNumber()) : name;
	}
}
