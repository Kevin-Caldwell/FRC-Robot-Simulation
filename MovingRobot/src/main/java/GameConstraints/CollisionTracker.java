package GameConstraints;

import java.awt.Point;

import Background.Robot;
import Background.RunRobot;

public class CollisionTracker {

    public static Point[] RobotBounds = new Point[4];
    public static Line[] RobotLines = new Line[4];
    public static Line[] FieldLines = new Line[4];

    public static void setFieldLines() {
        FieldLines[0] = new Line(FieldConstraints.UPPER_LEFT, FieldConstraints.UPPER_LEFT,
                FieldConstraints.UPPER_LEFT + FieldConstraints.WIDTH, FieldConstraints.UPPER_LEFT);
        FieldLines[1] = new Line(FieldConstraints.UPPER_LEFT + FieldConstraints.WIDTH, FieldConstraints.UPPER_LEFT,
                FieldConstraints.UPPER_LEFT + FieldConstraints.WIDTH,
                FieldConstraints.UPPER_LEFT + FieldConstraints.HEIGHT);
        FieldLines[2] = new Line(FieldConstraints.UPPER_LEFT + FieldConstraints.WIDTH,
                FieldConstraints.UPPER_LEFT + FieldConstraints.HEIGHT, FieldConstraints.UPPER_LEFT,
                FieldConstraints.UPPER_LEFT + FieldConstraints.HEIGHT);
        FieldLines[3] = new Line(FieldConstraints.UPPER_LEFT, FieldConstraints.UPPER_LEFT + FieldConstraints.HEIGHT,
                FieldConstraints.UPPER_LEFT, FieldConstraints.UPPER_LEFT);

        FieldLines[0] = new Line(0, 0, 0, 500);
        FieldLines[1] = new Line(0, 500, 500, 500);
        FieldLines[2] = new Line(500, 500, 500, 0);
        FieldLines[3] = new Line(500, 0, 0, 0);
    }

    public static void getBoundingPoints() {
        double tempx = (RunRobot.robot.ROBOT_LENGTH / 2) * Math.cos(Robot.theta)
                - (RunRobot.robot.ROBOT_WIDTH / 2) * Math.sin(Robot.theta);
        double tempy = (RunRobot.robot.ROBOT_LENGTH / 2) * Math.sin(Robot.theta)
                + (RunRobot.robot.ROBOT_WIDTH / 2) * Math.cos(Robot.theta);

        double tempx1 = (RunRobot.robot.ROBOT_LENGTH / 2) * (Math.cos(Math.PI + Robot.theta))
                - (RunRobot.robot.ROBOT_WIDTH / 2) * Math.sin(Math.PI - Robot.theta);
        double tempy1 = (RunRobot.robot.ROBOT_LENGTH / 2) * Math.sin(Math.PI - Robot.theta)
                + (RunRobot.robot.ROBOT_WIDTH / 2) * Math.cos(Math.PI - Robot.theta);

        RobotBounds[0] = new Point((int) Robot.posX - (int) tempx, (int) Robot.posY - (int) tempy);
        RobotBounds[1] = new Point((int) Robot.posX - (int) tempx1, (int) Robot.posY + (int) tempy1);
        RobotBounds[2] = new Point((int) Robot.posX + (int) tempx, (int) Robot.posY + (int) tempy);
        RobotBounds[3] = new Point((int) Robot.posX + (int) tempx1, (int) Robot.posY - (int) tempy1);

        RobotLines[0] = new Line(RobotBounds[0], RobotBounds[1]);
        RobotLines[1] = new Line(RobotBounds[1], RobotBounds[2]);
        RobotLines[2] = new Line(RobotBounds[2], RobotBounds[3]);
        RobotLines[3] = new Line(RobotBounds[3], RobotBounds[0]);
    }

    public static Point GetPointOfIntersection(Line l1, Line l2) {
        return new Point((int) ((l1.b * l2.c - l2.b * l1.c) / (l1.a * l2.b - l2.a * l1.b)),
                (int) ((l2.a * l1.c - l1.a * l2.c) / (l1.a * l2.b - l2.a * l1.b)));
    }

    public static boolean onLineSeg(Point p, Line l) {
        if ((int) getDistance(p, new Point((int) l.x1, (int) l.y1))
                + (int) getDistance(p, new Point((int) l.x2, (int) l.y2)) == (int) getDistance(
                        new Point((int) l.x1, (int) l.y1), new Point((int) l.x2, (int) l.y2)))
            return true;
        return false;
    }

    public static double getDistance(Point p1, Point p2) {
        return Math.sqrt((p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y));
    }

    public static boolean collided(Line l1, Line l2) {
        double collx = (l1.b * l2.c - l2.b * l1.c) / (l1.a * l2.b - l2.a * l1.b);
        double colly = (l2.a * l1.c - l1.a * l2.c) / (l1.a * l2.b - l2.a * l1.b);
        Point p = new Point((int) collx, (int) colly);

        if (onLineSeg(p, l1) && onLineSeg(p, l2)) {
            return true;
        }
        return false;
    }

    public static boolean robotCollided() {
        for (Line l1 : RobotLines) {
            for (Line l2 : FieldLines) {
                if (collided(l1, l2)) {
                    return true;
                }
            }
        }

        for (Obstacle obs : RunRobot.obsList) {
            for (Line l1 : RobotLines) {
                for (Line l2 : obs.lines) {
                    if (collided(l1, l2)) {
                        return true;
                    }
                }
            }

        }
        return false;
    }
}
