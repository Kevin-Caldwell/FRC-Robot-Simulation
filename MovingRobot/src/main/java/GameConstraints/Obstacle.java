package GameConstraints;

import java.awt.Point;
import java.util.ArrayList;

public class Obstacle {
    ArrayList<Point> pts;
    public ArrayList<Line> lines;

    public Obstacle() {

    }

    public Obstacle(ArrayList<Point> pts) {
        lines = new ArrayList<Line>();
        for (int i = 0; i < pts.size() - 1; i++) {
            lines.add(new Line(pts.get(i), pts.get(i + 1)));
        }
        lines.add(new Line(pts.get(0), pts.get(pts.size() - 1)));
    }

    public static Obstacle getObs1() {
        ArrayList<Point> pts = new ArrayList<Point>();
        pts.add(new Point(200, 230));
        pts.add(new Point(230, 230));
        pts.add(new Point(210, 210));
        return new Obstacle(pts);
    }
}
