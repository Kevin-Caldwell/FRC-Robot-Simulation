package GameConstraints;

import java.awt.Point;
import java.util.ArrayList;

/**
 * Obstacle class stores all the vertices and edges of the obstacles.
 */
public class Obstacle {
    /**
     * ArrayList which contains all the Vertices of the Obstacle
     */
    ArrayList<Point> pts;

    /**
     * ArrayList which contains all the Edges of the Obstacle
     */
    public ArrayList<Line> lines;

    /**
     * Empty constuctor for ArrayList Initialization
     */
    public Obstacle() {

    }

    /**
     * Constructor stores Points and lines of Obstacle.
     * @param pts An Arraylist containing all the points in the Obstacle
     */
    public Obstacle(ArrayList<Point> pts) {
        lines = new ArrayList<Line>();
        for (int i = 0; i < pts.size() - 1; i++) {
            lines.add(new Line(pts.get(i), pts.get(i + 1)));
        }
        lines.add(new Line(pts.get(0), pts.get(pts.size() - 1)));
    }

    /**
     * Returns Preset Obstacle
     * @return Obstacle
     */
    public static Obstacle getObs1() {
        ArrayList<Point> pts = new ArrayList<Point>();
        pts.add(new Point(200, 230));
        pts.add(new Point(230, 230));
        pts.add(new Point(210, 210));
        return new Obstacle(pts);
    }
}
