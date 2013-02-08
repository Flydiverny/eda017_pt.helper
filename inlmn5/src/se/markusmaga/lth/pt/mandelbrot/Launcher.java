package se.markusmaga.lth.pt.mandelbrot;

import se.markusmaga.lth.pt.helper.options.*;
import se.markusmaga.lth.pt.helper.*;

import se.lth.cs.pt.dots.listeners.DotWindow;

public class Launcher extends OptionsLauncher {
	private static final int WINDOW_WIDTH = 500;
	private static final int WINDOW_HEIGHT = 500;
	private static final int PIXEL_SIZE = 1;
	private static final int MAX_ITERATIONS = 255;
	
	public static void main(String[] args) {
		new Launcher().run();
	}
	
	private void initiate() {
		int width = getIntValue("width");
		int height = getIntValue("height");
		int pixels = getIntValue("pixels");
		int iterations = getIntValue("iterations");
		DotWindow w = new DotWindow(width, height, 1);
		w.useRectangularDots();
		w.setAutoUpdate(false);
		
		Mandelbrot m = new Mandelbrot(iterations);
		m.display(w);
	}

	/**
	 * Sets up the menu alternatives and their callbacks.
	 */
	@Override
	protected void setupMenu() {
		addMenuAction("Visa mandelbrot", new IFunction() {
			public void execute() {
				initiate();
			}
		});
	}
	
	@Override
	protected void setupOptions() {
		addOption("width", "Ange fönster bredd", WINDOW_WIDTH);
		addOption("height", "Ange fönster höjd", WINDOW_HEIGHT);
		addOption("pixels", "Ange pixel storlek", PIXEL_SIZE);
		addOption("iterations", "Ange pixel storlek", MAX_ITERATIONS);
	}
}