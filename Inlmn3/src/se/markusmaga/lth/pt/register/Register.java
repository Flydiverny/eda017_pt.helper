package se.markusmaga.lth.pt.register;

import java.util.*;

public class Register {
	private ArrayList<Person> persons;		// Holds our persons.

	/**
	 * Creates a new Register
	 */
	public Register() {
		persons = new ArrayList<Person>();
	}
	
	/**
	 * Creates a new Person and inserts to register.
	 * @param name - Name of person
	 * @param nbr - Number for person
	 */
	public boolean insert(String name, String nbr) {
		if (containsName(name)) return false;
		
		boolean added = persons.add(new Person(name, nbr));
		
		Collections.sort(persons); // Make sure we are sorted \o\
		
		return added;
	}
	
	/**
	 * Removes all persons with input name from register.
	 * @param name - name of person to remove
	 */
	public boolean remove(String name) {
		for (Person p : persons) {
			if (p.getName().equals(name)) {
				return persons.remove(p);
			}
		}
		
		return false;
	}
	
	/**
	 * Checks if input name exists in the register.
	 * @param name - name to check.
	 */
	public boolean containsName(String name) {
		for (Person p : persons) {
			if (p.getName().equals(name)) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Searches for input name in register and returns all partial hits.
	 * Lists all persons with names containing partOfName.
	 * @param partOfName - partOfName to look for in names.
	 */
	public List<Person> findByPartOfName(String partOfName) {
		List<Person> results = new ArrayList<Person>();
		
		for (Person p : persons) {
			if (p.getName().toLowerCase().contains(partOfName.toLowerCase())) {
				results.add(p);
			}
		}
		
		return results;
	}
	
	/**
	 * Searches for input number in register and returns all hits.
	 * Lists all persons with specified number.
	 * @param nbr - Number to find persons for.
	 */
	public List<Person> findByNumber(String nbr) {
		List<Person> results = new ArrayList<Person>();
		
		for (Person p : persons) {
			if (p.getNumber().toLowerCase().equals(nbr.toLowerCase())) {
				results.add(p);
			}
		}
		
		return results;
	}
	
	/**
	 * Returns a clone of the registers list.
	 */
	public List<Person> findAll() {
		return new ArrayList<Person>(persons);
	}
}
