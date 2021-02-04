package GameConstraints;

import java.awt.Point;

public class Line {
    public double a = 0;
    public double b = 0;
    public double c = 0;
    public double x1 = 0;
    public double y1 = 0;
    public double x2 = 0;
    public double y2 = 0;

    public Line(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public Line(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;

        a = y1 - y2;
        b = x2 - x1;
        c = x1 * y2 - x2 * y1;
    }

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
