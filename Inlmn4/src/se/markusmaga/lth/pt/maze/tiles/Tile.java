package se.markusmaga.lth.pt.maze.tiles;

import se.markusmaga.lth.pt.maze.Coord;

public class Tile {
	int x, y, type;
	
	public Tile(int x, int y, int type) {
		this.x = x;
		this.y = y;
		this.type = type;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public int getType() {
		return this.type;
	}
	
	public Coord getCoord() {
		return new Coord(x, y);
	}
}