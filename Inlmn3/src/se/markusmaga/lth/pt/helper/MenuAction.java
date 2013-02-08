package se.markusmaga.lth.pt.helper;

public class MenuAction {
	private String text;		// Holds description
	private IFunction f;		// Holds callback function.

	/**
	 * Creates a new MenuAction
	 * @param text - Description of menu action.
	 * @param f - callback function to be executed if choosen.
	 */
	public MenuAction(String text, IFunction f) {
		this.text = text;
		this.f = f;
	}
	
	/**
	 * Executes callback function.
	 * Should be run if MenuAction is choosen.
	 */
	public void execute() {
		f.execute();
	}

	/**
	 * Prints MenuAction description.
	 */
	public String toString() {
		return text;
	}
}