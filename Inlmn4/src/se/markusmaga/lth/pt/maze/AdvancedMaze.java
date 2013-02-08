package se.markusmaga.lth.pt.maze;

import se.markusmaga.lth.pt.maze.tiles.*;

import java.awt.Point;
import java.util.*;

public class AdvancedMaze extends Maze {
	public static final int MAZE_TELEPORT = 3;
	public static final int MAZE_DEATH = 4;

	Tile[][] advancedMazeMatrix;
	
	public AdvancedMaze(int width, int height) {
		super(width, height);
		
		advancedGenerate();
	}
	
	private void advancedGenerate() {
		advancedMazeMatrix = new Tile[getWidth()][getHeight()];
		
		List<Coord> teleportTiles = new ArrayList<Coord>();
		
		for(int x = 0; x < getWidth(); x++) {
			for(int y = 0; y < getHeight(); y++) {
				if(isWalkable(x, y)) {
					if(isDeadEnd(x, y)) {
						teleportTiles.add(new Coord(x,y));
					} else {
						advancedMazeMatrix[x][y] = new Tile(x, y, MAZE_PATH);
					}
				} else {
					advancedMazeMatrix[x][y] = new Tile(x, y, MAZE_WALL);
				}
			}
		}
		
		Random rnd = new Random();
		int targetIndex = rnd.nextInt(teleportTiles.size());
		
		if(teleportTiles.size() % 2 == 1) {
			Coord c = teleportTiles.remove(targetIndex);
			advancedMazeMatrix[c.getX()][c.getY()] = new Tile(c.getX(), c.getY(), MAZE_DEATH);
		}
		
		do {
			Coord tileCoord = teleportTiles.remove(0); // get and remove
			TeleportTile tp = new TeleportTile(tileCoord.getX(), tileCoord.getY(), MAZE_TELEPORT);
			
			targetIndex = rnd.nextInt(teleportTiles.size());			
			Coord tarCoord = teleportTiles.remove(targetIndex); // get and remove
			
			TeleportTile tar = new TeleportTile(tarCoord.getX(), tarCoord.getY(), MAZE_TELEPORT);
			
			advancedMazeMatrix[tar.getX()][tar.getY()] = tar;
			advancedMazeMatrix[tp.getX()][tp.getY()] = tp;
						
			tp.linkTeleport(tar);
		} while(teleportTiles.size() > 0);
	}
	
	public int getMazeType(int x, int y) {
		return advancedMazeMatrix[x][y].getType();
	}
	
	public boolean isDeadEnd(int x, int y) {
		int paths = 0;
		
		if(x == 0 || (x+1) == getWidth() || y == 0 || (y+1) == getHeight()) 
			return false;
		
		if(isWalkable(x-1, y))
			paths++;
		if(isWalkable(x+1, y))
			paths++;
		if(isWalkable(x, y+1))
			paths++;
		if(isWalkable(x, y-1))
			paths++;
			
		return paths == 1;
	}
	
	public Coord onStep(int x, int y) {
		switch(advancedMazeMatrix[x][y].getType()) {
		case MAZE_TELEPORT:
			return ((TeleportTile)advancedMazeMatrix[x][y]).getEndpoint().getCoord();
		case MAZE_DEATH:
			return new Coord(getStartX(), getStartY());
		default:
			return super.onStep(x, y);
		}
	}
}