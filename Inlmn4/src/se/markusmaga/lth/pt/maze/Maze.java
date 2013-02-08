package se.markusmaga.lth.pt.maze;

import se.lth.cs.pt.maze.MazeGenerator;

public class Maze {
	public static final int MAZE_PATH = 0;
	public static final int MAZE_WALL = 1;
	public static final int MAZE_GHOST = 2;
	
	private int startX = 0;
	private int startY;
	
	private int width, height;
	
	private int[][] mazeMatrix;

	/**
	 * Maze Constructor
	 * Width & Height should be uneven numbers, if not they will automatically be increased by one.
	 * @param width - uneven number
	 * @param height - uneven number
	 */
	public Maze(int width, int height) {
		this.width = width;
		this.height = height;
		
		generateMaze();
	}
	
	private void generateMaze() {
		mazeMatrix = MazeGenerator.generate(this.width, this.height);
		
		// Incase we inserted even numbers.
		this.width = mazeMatrix.length;
		this.height = mazeMatrix[0].length;
		
		setStartPosition();
	}
	
	private void setStartPosition() {
		for(int y=0; y < this.height; y++) {
			if(isWalkable(startX, y)) {
				startY = y;
				return;
			}
		}
	}
	
	public Coord onStep(int x, int y) {
		return new Coord(x, y);
	}
	
	protected void setMazeType(int x, int y, int type) {
		mazeMatrix[x][y] = type;
	}
	
	public int getMazeType(int x, int y) {
		return mazeMatrix[x][y];
	}
	
	public int getStartX() {
		return this.startX;
	}
	
	public int getStartY() {
		return this.startY;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public boolean isWalkable(int x, int y) {
		return mazeMatrix[x][y] != MAZE_WALL;
	}
}