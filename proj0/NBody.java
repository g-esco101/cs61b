public class NBody {

    /** 
     * Path of the background image.
     */
	private static final String backgroundImg = "./images/starfield.jpg";

    /**
     * Reads the radius of the universe from a file.
     *
     * @param  filename the path of the file.
     * @return the radius of the universe.
     */
	public static double readRadius(String filename) {
		In in = new In(filename);
		in.readInt();
		return in.readDouble();
	}

    /**
     * Creates an array of bodies from an input file.
     *
     * @param  filename the path of the file.
     * @return an array of all the bodies.
     */
	public static Body[] readBodies(String filename) {
		In in = new In(filename);
		Body[] allBodies = new Body[in.readInt()];
		in.readDouble();
		for (int i = 0; i < allBodies.length; i++) {
			Body b = new Body(in.readDouble(), in.readDouble(), in.readDouble(), 
				in.readDouble(), in.readDouble(), in.readString());
			allBodies[i] = b;
		}
		return allBodies;
	}

    /**
     * Prints the state of all the bodies
     *
     * @param  radius the radius of the universe.
     * @param  allBodies an array of all the bodies in the universe.
     */
	public static void printUniverseState(double radius, Body... allBodies) {
		StdOut.printf("%d\n", allBodies.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < allBodies.length; i++) {
    		StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  allBodies[i].xxPos, allBodies[i].yyPos, allBodies[i].xxVel,
                  allBodies[i].yyVel, allBodies[i].mass, allBodies[i].imgFileName);   
		}
	}

    /**
     * Runs the simulation of the universe and bodies. 
     *
     * @param  args the time to run the simulation, time step size, & the file that contains
     * the number of bodies, the radius, & data about the bodies. 
     */
	public static void main(String... args) {
		double T = Double.valueOf(args[0]);
		double dt = Double.valueOf(args[1]);
		String filename = args[2];
		double radius = readRadius(filename);
		Body[] allBodies = readBodies(filename);
		/** Sets up the universe so it goes from
		  * -radius, -radius up to radius, radius */		
		StdDraw.setScale(-radius, radius);
		StdDraw.clear();
		/** Draws the background image in a rectangular pattern. */
        StdDraw.picture(0, 0, "images/starfield.jpg");
		for (Body b : allBodies) {
			b.draw();
		}
		/** Enables double buffering.
		  * A animation technique where all drawing takes place on the offscreen canvas.
		  * Only when you call show() does your drawing get copied from the
		  * offscreen canvas to the onscreen canvas, where it is displayed
		  * in the standard drawing window. */
		StdDraw.enableDoubleBuffering();
		for (double time = 0; time <= T; time += dt) {
			double[] xForces = new double[allBodies.length];
			double[] yForces = new double[allBodies.length];
			for (int i = 0 ; i < allBodies.length; i++) {
				xForces[i] = allBodies[i].calcNetForceExertedByX(allBodies);
				yForces[i] = allBodies[i].calcNetForceExertedByY(allBodies);	
			}
			// Instructed to invode update after all forces are calculated.
			for (int i = 0 ; i < allBodies.length; i++) {
				allBodies[i].update(dt, xForces[i], yForces[i]);
			}	
	        StdDraw.picture(0, 0, "images/starfield.jpg");
			for (Body b : allBodies) {
				b.draw();
			}
			/** Shows the drawing to the screen, and waits 2000 milliseconds. */
			StdDraw.show();
			StdDraw.pause(10);
		}
		printUniverseState(radius, allBodies);
	}
}
