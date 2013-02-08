import se.lth.cs.pt.graphics.basic.GraphicsWindow;
import se.lth.cs.pt.io.Keyboard;
import se.lth.cs.pt.turtle.visible.Turtle;
import se.lth.cs.pt.graphics.Color;
import se.lth.cs.pt.die.Die;
import java.util.*;

class TurtleRace {

	private List<Turtle> turtles;

	public static final double	LINE_TOP_Y		= 50;
	public static final double	LINE_BOT_Y		= 250;
	public static final double	START_LINE_X	= 50;
	public static final double	FINISH_LINE_X	= 450;
	public static final int		TURTLE_SPEED	= 5;

    public static void main(String[] args) {
        new TurtleRace().run();
    }
	
	/**
	 * Le method a la Run
	 */
	void run() {
		int antal = getAmountOfTurtles(); // Läs in önskat antal paddiz

		turtles = new ArrayList<Turtle>(); // Initisera lista.

		// Skapa ett fint fönster
		GraphicsWindow w = createWindow(500,300);
		
		// Avstånd mellan turtles. (+1 för space till kanter)
		double turtleSpace = (LINE_BOT_Y - LINE_TOP_Y) / (antal + 1); 
		
		// Skapa Turtles
		for (int i = 1; i <= antal; i++) {
			createTurtle(w, antal, LINE_TOP_Y + turtleSpace * i);
		}

		race();
	}

	/**
	 * Skapar ett fönster
	 * @param width Fönster bredd
	 * @param height Fönster höjd
	 */
	private GraphicsWindow createWindow(int width, int height) {
		GraphicsWindow w = new GraphicsWindow(width, height);
		
		w.drawLine(START_LINE_X, LINE_TOP_Y, START_LINE_X, LINE_BOT_Y, 2, Color.RED);
		w.drawLine(FINISH_LINE_X, LINE_TOP_Y, FINISH_LINE_X, LINE_BOT_Y, 2, Color.RED);	

		return w;
	}
	
	/**
	 * Skapar ett fönster
	 */
	private void race() {
		Die d = new Die();
	
		do {
			for (Turtle t : turtles) {
				d.roll();
				t.forward(d.getNbrOfSpots());
			}
		} while (noWinner());
		
		congratulateWinners();
	}
	
	/**
	 * Gratulerar vinnar sköldpaddizarna
	 */
	private void congratulateWinners() {
		String outputWinners = "";
		int winners = 0, winner=0;
		
		for (int i = 0; i < turtles.size(); i++) {
			if (turtles.get(i).getX() < FINISH_LINE_X) continue;
			
			outputWinners += (i+1) + " "; // i+1 = human readable
			winners++;
			winner = i;
		}
		
		if (winners == 1) {
			int steps = (int) (turtles.get(winner).getX() - START_LINE_X);
			outputWinners += "- hen har gått " + steps + " steg.";
		}

		System.out.println("Vinnare blev: " + outputWinners);
	}
	
	/**
	 * Kontrollerar ifall någon sköldpaddiz har passerat mållinjen.
	 */
	private boolean noWinner() {
		for (Turtle t : turtles) {
			if (t.getX() > FINISH_LINE_X) return false;
		}
		return true;
	}
	
	/**
	 * Läser in antal önskade sköldpaddor från användaren
	 */
	private int getAmountOfTurtles() {
		return Keyboard.nextInt("Ange antal sköldpaddor: ");
	}
	
	/**
	 * Skapar en sköldpadda i fönstret w, sätter speed beroende på antal
	 * @param w Fönster att skapa sköldpaddan i
	 * @param speed Speed modifier
	 * @param yPos Position i y-led att skapa sköldpaddan på
	 */
	private void createTurtle(GraphicsWindow w, int speed, double yPos) {
		Turtle t = new Turtle(w, START_LINE_X, yPos);
		t.setSpeed(speed); //Snabba upp lite med synliga paddor
		turtles.add(t);
		t.right(90);
		t.penDown();
	}
}
