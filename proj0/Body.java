import java.util.Objects;

public class Body {

    /** 
     * Gravitational constant  
     */
	private static final double G = 6.67e-11;

    /** 
     * Current x position  
     */
	public double xxPos;

    /** 
     * Current y position  
     */
	public double yyPos;

    /** 
     * Current x velocity  
     */
	public double xxVel;

    /** 
     * Current y velocity  
     */
	public double yyVel;

    /** 
     * Current x position  
     */
	public double mass;

    /** 
     * File name of the image
     */
	public String imgFileName;

    /** 
     * Path to image file
     */
	private static final String imgs = "images/";

    /**
     * Constructs a Body with the specified values.
     *
     * @param  xxPox the initial x-position
     * @param  yyPos the initial y-position
     * @param  xxVel the initial x-velocity
     * @param  yyVel the initial y-velocity
     * @param  mass the mass
     * @param  imgFileName name of image file
     */
	public Body(double xxPos, double yyPos, double xxVel, double yyVel, double mass, String imgFileName) {
		this.xxPos = xxPos;
		this.yyPos = yyPos;
		this.xxVel = xxVel;
		this.yyVel = yyVel;
		this.mass = mass;
		this.imgFileName = imgFileName;
	}

    /**
     * Constructs a Body from the specified Body.
     *
     * @param  b the Body used to construct a bodyinitial x-position
     */
	public Body (Body b) {
		this.xxPos = b.xxPos;
		this.yyPos = b.yyPos;
		this.xxVel = b.xxVel;
		this.yyVel = b.yyVel;
		this.mass = b.mass;
		this.imgFileName = b.imgFileName;
	}

    /**
     * Calculates the distance to Body b.
     *
     * @param  b the body to determine the distance to.
     * @return the distance.
     */
     public double calcDistance(Body b) {
		double dx = b.xxPos - this.xxPos;
		double dy = b.yyPos - this.yyPos;
		return Math.hypot(dx, dy);
	}

    /**
     * Calculates the force exerted by Body b.
     *
     * @param  b the body that exerts the force.
     * @return the force exerted.
     */	
    public double calcForceExertedBy(Body b) {
		return this.G * this.mass * b.mass / Math.pow(calcDistance(b), 2);
	}

    /**
     * Calculates the x-component of the force exerted by Body b.
     *
     * @param  b the body that exerts the force.
     * @return the x-component of the force exerted.
     */
	public double calcForceExertedByX(Body b) {
		return calcForceExertedBy(b) * (b.xxPos - this.xxPos) / calcDistance(b);
	}

    /**
     * Calculates the y-component of the force exerted by Body b.
     *
     * @param  b the body that exerts the force.
     * @return the y-component of the force exerted.
     */
	public double calcForceExertedByY(Body b) {
		return calcForceExertedBy(b) * (b.yyPos - this.yyPos) / calcDistance(b);
	}

    /**
     * Calculates the x-component of the force exerted by all the bodies.
     *
     * @param  allBodies an array of all the bodies that exerts the force.
     * @return the x-component of the force exerted by all the bodies.
     */
	public double calcNetForceExertedByX(Body... allBodies) {
		double netForceX = 0;
		for (Body b : allBodies) {
			if (!(this.equals(b))) {
				netForceX += calcForceExertedByX(b);
			}
		}
		return netForceX;
	}

    /**
     * Calculates the y-component of the force exerted by all the bodies.
     *
     * @param  allBodies an array of all the bodies that exerts the force.
     * @return the y-component of the force exerted by all the bodies.
     */
	public double calcNetForceExertedByY(Body... allBodies) {
		double netForceY = 0;
		for (Body b : allBodies) {
			if (!(this.equals(b))) {
				netForceY += calcForceExertedByY(b);
			}
		}
		return netForceY;
	}

    /**
     * Updates the velocity and position according to the forces exerted on this Body.
     *
     * @param  dt the time step.
     * @param  fX the x-component of the force exerted on this Body.
     * @param  fY the y-component of the force exerted on this Body.
     */
	public void update(double dt, double fX, double fY) {
		double aX = fX / this.mass;
		double aY = fY / this.mass;
		this.xxVel = this.xxVel + aX * dt;
		this.yyVel = this.yyVel + aY * dt;
		this.xxPos = this.xxPos + this.xxVel * dt;
		this.yyPos = this.yyPos + this.yyVel * dt;
	}

    /**
     * Draws the image to the draw window.
     *
     */
	public void draw() {
		StdDraw.picture(this.xxPos, this.yyPos, imgs + this.imgFileName);
	}

    /**
     * Determines if this Body is equal to o.
     *
     * @param o the Body to check if equal.
     * @return whether they are equal or not.
     */
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Body)) {
			return false;
		}
		Body b = (Body) o;
		return Double.compare(this.xxPos, b.xxPos) == 0 && Double.compare(this.yyPos, b.yyPos) == 0 && Double.compare(this.xxVel, b.xxVel) == 0 && Double.compare(this.yyVel, b.yyVel) == 0 && Double.compare(this.mass, b.mass) == 0;
	}

    /**
     * Calculates the hash value.
     *
     * @return the hashvalue.
     */
	@Override
	public int hashCode() {
		return Objects.hash(this.xxPos, this.yyPos, this.xxVel, this.yyVel, this.mass);
	}
}
