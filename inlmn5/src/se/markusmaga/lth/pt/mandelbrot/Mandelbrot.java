package se.markusmaga.lth.pt.mandelbrot;

import se.lth.cs.pt.dots.DotWindow_;
import se.lth.cs.pt.dots.Color;
import java.awt.Point;

public class Mandelbrot {
	public static final int MAX_ITERATIONS	= 255;
	public static final double START_SCALE	= 2;
	public static final double START_RE		= -0.5;
	public static final double START_IM		= 0.0;
	
	private int max_iterations;
	private double currentScale;
	private double targetRe, targetIm;
	
	public Mandelbrot() {
		this(MAX_ITERATIONS, START_RE, START_IM, START_SCALE);
	}
	
	public Mandelbrot(int max_iterations) {
		this(max_iterations, START_RE, START_IM, START_SCALE);
	}
	
	public Mandelbrot(int max_iterations, double re, double im, double scale) {
		this.max_iterations	= max_iterations;
		this.targetRe		= re;
		this.targetIm		= im;
		this.currentScale	= scale;
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
	
	public void increaseScale() {
		this.currentScale *= 1.1;
	}
	
	public void decreaseScale() {
		this.currentScale *= 0.9;
	}
	
	public void setScale(double scale) {
		this.currentScale = scale;
	}
	
	public void setCenter(double re, double im) {
		this.targetRe = re;
		this.targetIm = im;
	}
	
	public void setCenterByCoordinates(int x, int y, int width, int height) {
		setCenter(xToRe(x, width), yToIm(y, height));
	}
	
	private double xToRe(double x, double width) {
		return coordToComplex(x, width, targetRe);
	}
	
	private double yToIm(double y, double height) {
		return coordToComplex(y, height, targetIm);
	}
	
	private double coordToComplex(double c, double dim, double tar) {
		return (currentScale/2-tar)*-1+currentScale/dim*c;
	}
	
	public void display(DotWindow_ w) {
		w.setAutoUpdate(false);
	
		for(int x = 0; x < w.getWidth(); x++) {
			for(int y = 0; y < w.getHeight(); y++) {
				double im = yToIm(y, w.getHeight());
				double re = xToRe(x, w.getWidth());
				
				int iterations = calculate(re, im);
				w.setDot(x, y, getColor(iterations));
			}
		}
		
		w.update();
		w.setAutoUpdate(true);
	}
	
	public Color getColor(int it) {
		double c = 255.0/this.max_iterations*it;
		
		if(it < this.max_iterations/3)
			return new Color(127, 127-(int) (c/2.0), 127);
		else if(it < this.max_iterations/3*2)
			return new Color(0, 0, (int) c);
		else
			return new Color(255-(int) c, 0, 0);
	}
}