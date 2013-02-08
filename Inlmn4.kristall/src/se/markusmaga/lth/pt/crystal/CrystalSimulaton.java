package se.markusmaga.lth.pt.crystal;

import java.utils.*;

class CrystalSimulation {
	private static final int START_RADIUS_OFFSET = 10;

	private List<Ion> movingIons;
	private int radius, startradius;
	private boolean[][] crystalized;
	
	public CrystalSimulation(int radius) {
		this.radius = radius;
		this.startradius = radius - START_RADIUS_OFFSET;
		
		movingIons = new ArrayList<Ion>();
		crystalized = new boolean[radius][radius];
	}
}