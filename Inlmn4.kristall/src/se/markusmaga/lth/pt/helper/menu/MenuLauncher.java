package se.markusmaga.lth.pt.helper.menu;

import se.markusmaga.lth.pt.helper.IFunction;

public abstract class MenuLauncher {
	private IMenuHandler mh;

	public MenuLauncher() {
		mh = new MenuHandler();
	}
	
	/**
	 * Loops menu
	 */
	public void run() {
		addMenuAction("Exit", new IFunction() {
			public void execute() {
				System.exit(0);
			}
		});

		setupMenu();
		
		while(true) {
			mh.showMenuAndExecuteAction();
		}
	}
	
	/**
	 * Adds a menu alternative.
	 */
	protected void addMenuAction(String text, IFunction f) {
		mh.add(new MenuAction(text, f));
	}
	
	/**
	 * Sets up the menu alternatives and their callbacks.
	 */
	protected abstract void setupMenu();
}