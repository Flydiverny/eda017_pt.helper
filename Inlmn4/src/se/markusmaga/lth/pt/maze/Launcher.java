package se.markusmaga.lth.pt.maze;

import se.markusmaga.lth.pt.helper.*;

import se.lth.cs.pt.io.Keyboard;

public class Launcher {
	private MenuHandler mh;
	private boolean configured = false;
	
	private int mazeWidth, mazeHeight, mazeSize;
	
	public static void main(String[] args) {
		new Launcher().run();
	}
	
	/**
	 * Runs the program
	 */
	void run() {
		setupMenu();
		
		while(true) {
			mh.showMenuAndExecuteAction();
		}
	}
	
	private void launchGame(boolean advanced) {
		if(!configured) {
		 	new MazeHandler(advanced);
		} else {
			new MazeHandler(mazeWidth, mazeHeight, mazeSize, advanced);
		}
	}
	
	private void configureMaze() {
		System.out.println("Configure maze size, width & height.");
	
		try {
			mazeWidth = Keyboard.nextInt("Maze Width: ");
			mazeHeight = Keyboard.nextInt("Maze Height: ");
			mazeSize = Keyboard.nextInt("Maze Size: ");
			
			configured = true;
		} catch(Exception e) {
			System.out.println("Invalid input!");
			configured = false;
		}
	}
	
	/**
	 * Sets up the menu alternatives and their callbacks.
	 */
	private void setupMenu() {
		mh = new MenuHandler();
		
		mh.add(new MenuAction("Exit", new IFunction() {
			public void execute() {
				System.exit(0);
			}
		}));
		
		mh.add(new MenuAction("Play Maze", new IFunction() {
			public void execute() {
				launchGame(false);
			}
		}));
		
		mh.add(new MenuAction("Play Advanced Maze", new IFunction() {
			public void execute() {
				launchGame(true);
			}
		}));
		
		mh.add(new MenuAction("Configure Maze", new IFunction() {
			public void execute() {
				configureMaze();
			}
		}));
	}
}