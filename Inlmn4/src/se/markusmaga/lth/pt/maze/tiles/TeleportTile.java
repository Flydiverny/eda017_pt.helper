package se.markusmaga.lth.pt.maze.tiles;

public class TeleportTile extends Tile {
	Tile endpoint;

	public TeleportTile(int x, int y, int type) {
		super(x, y, type);
	}
	
	public void linkTeleport(TeleportTile t) {
		t.endpoint = this;
		this.endpoint = t;
	}
	
	public Tile getEndpoint() {
		return this.endpoint;
	}
}