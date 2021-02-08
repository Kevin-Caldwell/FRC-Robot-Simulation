package GameConstraints;

import java.awt.Point;

/**
 * Line class used for tracking collisions. 
 * Stores lines of the form:
 * <p>ax + by + c = 0</p>
 * Between Points (x1, y1) and (x2, y2).
 * 
 * @author Kevin Caldwell
 */
public class Line {
    public double a = 0;
    public double b = 0;
    public double c = 0;
    public double x1 = 0;
    public double y1 = 0;
    public double x2 = 0;
    public double y2 = 0;

    /**
     * Constructor which takes a, b, c as inputs
     * @param a
     * @param b
     * @param c
     */
    public Line(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    /**
     * Contructor with 2 Points, (x1, y1) and (x2, y2) 
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;

        a = y1 - y2;
        b = x2 - x1;
        c = x1 * y2 - x2 * y1;
    }

    /**
     * Contructor with 2 Points, p1 and p2
     * @param p1
     * @param p2
     */
    public Line(Point p1, Point p2) {
        x1 = p1.getX();
        y1 = p1.getY();

        x2 = p2.getX();
        y2 = p2.getY();

        a = y1 - y2;
        b = x2 - x1;
        c = x1 * y2 - x2 * y1;
    }
}
