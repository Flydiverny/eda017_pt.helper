package se.markusmaga.lth.pt.register;

import se.markusmaga.lth.pt.helper.*;

import se.lth.cs.pt.io.IO;
import se.lth.cs.pt.io.Keyboard;
import java.util.*;
import java.io.PrintStream;

public class RegisterHandler {
	private Register register;		// Register
	private MenuHandler mh;			// MenuHandler for main menu

	public static void main(String[] args) {
		new RegisterHandler().run();
	}
	
	/**
	 * Runs the program
	 */
	void run() {
		register = new Register();
		setupMenu();
		
		while(true) {
			mh.showMenuAndExecuteAction();
		}
	}
	
	/**
	 * Add person to user by user specified name and number.
	 */
	private void addPerson() {
		String name = Keyboard.nextLine("Namn: ");
		
		if (register.containsName(name)) {
			System.out.println(name + " är redan inlagd");
			return;
		}
			
		String nbr = Keyboard.nextLine("Telefonnummer: ");
		
		if (register.insert(name, nbr)) {
			System.out.println("OK - " + name + " är nu inlagd.");
		} else {
			System.out.println(name + " är redan inlagd.");
		}
	}
	
	/**
	 * Remove person from register by user specified name.
	 */
	private void removePerson() {
		String name = Keyboard.nextLine("Namn: ");
			
		if(register.remove(name)) {
			System.out.println("OK - " + name + " är nu borttagen.");
		} else {
			System.out.println(name + " finns inte i registret.");
		}
	}
	
	/**
	 * Search in register for person by user specified name.
	 */
	private void searchPersonByName() {
		String name = Keyboard.nextLine("Del av namn: ");
		
		List<Person> results = register.findByPartOfName(name);
		
		if (results.size() == 0) {
			System.out.println("Det finns ingen sådan person i registret.");
			return;
		}
		
		printPersonList(results);
	}
	
	/**
	 * Search in register for person by user specified number.
	 */
	private void searchPersonByNbr() {
		String nbr = Keyboard.nextLine("Nummer: ");
		
		List<Person> results = register.findByNumber(nbr);
		
		switch(results.size()) {
		case 0:
			System.out.println("Det finns ingen med detta nummer i registret.");
			return;
		case 1:
			System.out.println("Nummer " + nbr + " innehas av " + results.get(0).getName());
			return;
		default:
			System.out.println("Nummer " + nbr + " delas av:");
			
			for(Person p : results)
				System.out.println(" * " + p.getName());
				
			return;
		}
	}
		
	/**
	 * Prints list of persons to console.
	 * @param persons - list of persons.
	 */
	private void printPersonList(List<Person> persons) {
		for(Person p : persons) {
			System.out.println(p);
		}
	}
	
	/**
	 * Prints register to console.
	 */
	private void printRegister() {
		List<Person> personList = register.findAll();
		
		if (personList.size() == 0) {
			System.out.println("Det finns inga personer i registret.");
		} else {
			printPersonList(personList);
		}
	}
	
	/**
	 * Saves register to user specified file.
	 */
	private void saveRegister() {
		String fileName = Keyboard.nextLine("Spara till filnamn: ");
		List<Person> persons = register.findAll();
		
		PrintStream out = IO.fileWriter(fileName);
		for (Person p : persons) {
			out.println(p.getName());
			out.println(p.getNumber());
		}
		out.flush();
		out.close();
	}
	
	/**
	 * Adds Persons from user specified file to register.
	 */
	private void loadRegister() {
		String fileName = Keyboard.nextLine("Läs från filnamn: ");
		
		Scanner in = IO.fileScanner(fileName);

		while (in.hasNextLine()) {
			 String name = in.nextLine();
			 String number = in.nextLine();
			 if (!register.insert(name, number))
				System.out.println("Kunde inte lägga till " + name + " med nummer " + number);
		 }
	}
	
	/**
	 * Sets up the menu alternatives and their callbacks.
	 */
	private void setupMenu() {
		mh = new MenuHandler();
	
		mh.add(new MenuAction("Avsluta", new IFunction() {
			public void execute() {
				System.out.print("Avslutar program");
				System.exit(0);
			}
		}));
		
		mh.add(new MenuAction("Lägg in ny person", new IFunction() {
			public void execute() {
				addPerson();
			}
		}));
		
		mh.add(new MenuAction("Tag bort person", new IFunction() {
			public void execute() {
				removePerson();
			}
		}));
		
		mh.add(new MenuAction("Sök på del av namn", new IFunction() {
			public void execute() {
				searchPersonByName();
			}
		}));
		
		mh.add(new MenuAction("Se vem som har givet nummer", new IFunction() {
			public void execute() {
				searchPersonByNbr();
			}
		}));
		
		mh.add(new MenuAction("Skriv ut alla personer", new IFunction() {
			public void execute() {
				printRegister();
			}
		}));
		
		mh.add(new MenuAction("Spara register till fil", new IFunction() {
			public void execute() {
				saveRegister();
			}
		}));
		
		mh.add(new MenuAction("Ladda register från fil", new IFunction() {
			public void execute() {
				loadRegister();
			}
		}));
	}
}