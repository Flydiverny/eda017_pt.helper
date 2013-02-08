package se.markusmaga.lth.pt.mandelbrot;

import se.markusmaga.lth.pt.helper.menu.*;
import se.markusmaga.lth.pt.helper.*;

public class Launcher extends MenuLauncher {
	
	public static void main(String[] args) {
		new Launcher().run();
	}
	
	/**
	 * Sets up the menu alternatives and their callbacks.
	 */
	@Override
	protected void setupMenu() {
	
		addMenuAction("Exit", new IFunction() {
			public void execute() {
				System.exit(0);
			}
		});
	}
}