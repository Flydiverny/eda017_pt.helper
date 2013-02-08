package se.markusmaga.lth.pt.maze;

import se.lth.cs.pt.maze.MazeGenerator;
import se.lth.cs.pt.dots.*;
import se.lth.cs.pt.dots.events.*;

public class MazeHandler {
	public static final int MAZE_WIDTH = 31;
	public static final int MAZE_HEIGHT = 11;
	public static final int MAZE_SIZE = 20;
	
	public static final Color MAZE_COLOR_WALL = Color.BLACK;
	public static final Color MAZE_COLOR_PATH = Color.WHITE;
	public static final Color MAZE_COLOR_PLAYER = Color.RED;
	public static final Color MAZE_COLOR_TELEPORT = Color.BLUE;
	public static final Color MAZE_COLOR_DEATH = Color.GREEN;
	public static final Color MAZE_COLOR_GHOST = new Color(255,200,200);
	
	private int playerX = 0, playerY;
	private DotWindow gameWindow;
	private int[][] mazeMatrix ;

	private int mazeSize;
	
	private Maze maze;
	
	public MazeHandler() {
		this(MAZE_WIDTH, MAZE_HEIGHT, MAZE_SIZE);
	}
	
	public MazeHandler(boolean advanced) {
		this(MAZE_WIDTH, MAZE_HEIGHT, MAZE_SIZE, advanced);
	}
	
	public MazeHandler(int width, int height, int size) {
		this(width, height, size, false);
	}
	
	public MazeHandler(int width, int height, int size, boolean advanced) {
		this.mazeSize = size;
		
		if(!advanced) {
			maze = new Maze(width, height);
		} else {
			maze = new AdvancedMaze(width, height);
		}
		
		setupWindow();
	
		runGame();
	}
	
	private void setupWindow() {
		gameWindow = new DotWindow(maze.getWidth(), maze.getHeight(), mazeSize);
		gameWindow.useRectangularDots();
		gameWindow.checkKeys(true,false,false);
		
		paintMaze();
	}
	
	public void paintMaze() {
		DotWindow w = gameWindow; // lazy
		w.setAutoUpdate(false);
	
		for(int x=0; x < w.getWidth(); x++) {
			for(int y=0; y < w.getHeight(); y++) {
				switch(maze.getMazeType(x,y)) {
				case Maze.MAZE_PATH:
					w.setDot(x, y, MAZE_COLOR_PATH);
					continue;
				case Maze.MAZE_WALL:
					w.setDot(x, y, MAZE_COLOR_WALL);
					continue;
				case Maze.MAZE_GHOST:
					continue;
				case AdvancedMaze.MAZE_TELEPORT:
					w.setDot(x, y, MAZE_COLOR_TELEPORT);
					continue;
				case AdvancedMaze.MAZE_DEATH:
					w.setDot(x, y, MAZE_COLOR_DEATH);
					continue;
				default:
					System.out.println("Invalid MazeType");
					return;
				}
			}
		}
		
		// Paint start position
		playerX = maze.getStartX();
		playerY = maze.getStartY();
		w.setDot(playerX, playerY, MAZE_COLOR_PLAYER);
		
		
		w.update();
		w.setAutoUpdate(true);
	}
	
	private void runGame() {
		do {
			GameEvent e = gameWindow.getNextEvent();
			if(e.getKind() == GameEvent.KEY_PRESSED) {
				movePlayer(e.getKeyCode());
			}

		} while(!checkWin());
		
		System.out.println("You won!");
		System.exit(0);
	}
	
	private boolean checkWin() {
		return (playerX+1) == maze.getWidth();
	}
	
	private void movePlayer(int keycode) {
		int newX=playerX, newY=playerY;
		
		switch(keycode) {
		case 37: // left
			newX -= 1;
			break;
		case 38: // up
			newY -= 1;
			break;
		case 39: // right
			newX += 1;
			break;
		case 40: // down
			newY += 1;
			break;
		default: // Invalid move
			return;
		}
		
		if(maze.isWalkable(newX, newY)) {
			Coord newPoint = maze.onStep(newX, newY);
			
			if(maze.getMazeType(playerX, playerY) == AdvancedMaze.MAZE_TELEPORT) {
				gameWindow.setDot(playerX, playerY, MAZE_COLOR_TELEPORT);
			} else {
				gameWindow.setDot(playerX, playerY, MAZE_COLOR_GHOST);
			}
			gameWindow.setDot(newPoint.getX(), newPoint.getY(), MAZE_COLOR_PLAYER);
			
			playerX = newPoint.getX();
			playerY = newPoint.getY();
		}
	}
}