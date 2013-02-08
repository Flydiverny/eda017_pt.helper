package se.markusmaga.lth.pt.mandelbrot;

import se.lth.cs.pt.dots.events.DotWindow;
import se.lth.cs.pt.dots.events.GameEvent;

public class MandelbrotWindow {
	private Mandelbrot m;
	private DotWindow w;
	
	public MandelbrotWindow(int width, int height, int size, int iterations) {
		this.w = new DotWindow(width, height, size);
		this.m = new Mandelbrot(iterations);
		
		setupWindow();
	}
	
	private void setupWindow() {
		w.useRectangularDots();
		w.setAutoUpdate(false);
		w.checkMouse(true, false, false, false, false);
		w.checkKeys(true, false, false);
	}
	
	private void mouseClicked(GameEvent event) {
		scaleChanger(event.getButton());
		m.setCenterByCoordinates(event.getX(), event.getY(), w.getWidth(), w.getHeight());
	}
	
	private void keyClicked(GameEvent event) {
		scaleChanger(event.getKeyCode());
	}
	
	private void scaleChanger(int modifier) {
		switch(modifier) {
		case 1:
		case 107:
			m.decreaseScale();
			break;
		case 3:
		case 109:
			m.increaseScale();
			break;
		}
	}
	
	private void mouseDragged(int x, int y, GameEvent event) {
		int x0 = event.getX();
		int y0 = event.getY();
		
		int xC = x0 > x ? x0-x : x - x0;
		int yC = y0 > y ? y0-y : y - y0;
		
		m.setCenterByCoordinates(xC, yC, w.getWidth(), w.getHeight());
	}
	
	public void looper() {
		int x=0, y=0;
		while(true) {
			m.display(w);
			
			GameEvent event = w.getNextEvent();
			switch (event.getKind()) {
			case GameEvent.MOUSE_CLICKED:
				mouseClicked(event);
				break;
			case GameEvent.MOUSE_DRAGGED:
				x = event.getX();
				y = event.getY();
				break;
			case GameEvent.MOUSE_RELEASED:
				mouseDragged(x, y, event);
				break;
			case GameEvent.KEY_PRESSED:
				keyClicked(event);
				break;
			}
		}
	}
}