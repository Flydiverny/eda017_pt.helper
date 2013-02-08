package se.markusmaga.lth.pt.mandelbrot;

import se.lth.cs.pt.dots.listeners.DotWindow;

public class Mandelbrot {
	public static final int MAX_ITERATIONS = 100;
	
	private int max_iterations;
	
	public Mandelbrot() {
		this(MAX_ITERATIONS);
	}
	
	public Mandelbrot(int max_iterations) {
		this.max_iterations=max_iterations;
	}
	
	private int calculate(double re, double im) {
		double a=0, b=0;
		
		int iterations=0;
		while( a*a + b*b < 2*2 && iterations < max_iterations) {
			// z = a + ib
			// z^2 = (a+ib)^2 = a^2 + 2abi - b^2
			// c = re + im
			// z2 = z^2 + c
			
			double atemp = a * a - b * b + re;
			b = 2 * a * b + im;
			a = atemp;
			iterations++;
		}
	
		return iterations;
	}
	
	public void display(DotWindow w) {
		for(int x = 0; x < w.getWidth(); x++) {
			
		}
	}
}