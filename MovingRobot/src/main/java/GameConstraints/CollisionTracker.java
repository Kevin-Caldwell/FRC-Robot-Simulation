package GameConstraints;

import Background.Robot;
import Background.RunRobot;
import MainCode.Subsystems.DriveBase;

/**
 * This class is used for calculating collisions.
 * 
 * @author Kevin Caldwell
 */
public class CollisionTracker extends Thread {

    /**
     * Array stores the 4 vertices of the rectangular robot.
     */
    public static Point[] RobotBounds = new Point[4];

    /**
     * Array stores the equations of the 4 edges of the rectangular robot.
     */
    public static Line[] RobotLines = new Line[4];

    /**
     * Array stores the equations of the 4 edges of the Field.
     */
    public static Line[] FieldLines = new Line[4];

    /**
     * Constructor starts Collision Thread
     */
    public CollisionTracker() {
        this.setName("Collision Thread");
    }

    /**
     * This is a method used to set the FieldLines of the Field. Called once.
     */
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

    /**
     * Calculates the Bounding points of the Robot, given the position of the Robot
     * and the angle of the Robot.
     */
    public static void getBoundingPoints() {
        double tempx = (Robot.ROBOT_LENGTH / 2) * Math.cos(Robot.theta)
                - (Robot.ROBOT_WIDTH / 2) * Math.sin(Robot.theta);
        double tempy = (Robot.ROBOT_LENGTH / 2) * Math.sin(Robot.theta)
                + (Robot.ROBOT_WIDTH / 2) * Math.cos(Robot.theta);

        double tempx1 = (Robot.ROBOT_LENGTH / 2) * (Math.cos(Math.PI + Robot.theta))
                - (Robot.ROBOT_WIDTH / 2) * Math.sin(Math.PI - Robot.theta);
        double tempy1 = (Robot.ROBOT_LENGTH / 2) * Math.sin(Math.PI - Robot.theta)
                + (Robot.ROBOT_WIDTH / 2) * Math.cos(Math.PI - Robot.theta);

        RobotBounds[0] = new Point((int) Robot.posX - (int) tempx, (int) Robot.posY - (int) tempy);
        RobotBounds[1] = new Point((int) Robot.posX - (int) tempx1, (int) Robot.posY + (int) tempy1);
        RobotBounds[2] = new Point((int) Robot.posX + (int) tempx, (int) Robot.posY + (int) tempy);
        RobotBounds[3] = new Point((int) Robot.posX + (int) tempx1, (int) Robot.posY - (int) tempy1);

        RobotLines[0] = new Line(RobotBounds[0], RobotBounds[1]);
        RobotLines[1] = new Line(RobotBounds[1], RobotBounds[2]);
        RobotLines[2] = new Line(RobotBounds[2], RobotBounds[3]);
        RobotLines[3] = new Line(RobotBounds[3], RobotBounds[0]);
    }

    /**
     * 
     * @param l1 The first Line
     * @param l2 The second Line
     * @return Point of intersection of l1 and l2
     * 
     */
    public static Point GetPointOfIntersection(Line l1, Line l2) {
        return new Point((int) ((l1.b * l2.c - l2.b * l1.c) / (l1.a * l2.b - l2.a * l1.b)),
                (int) ((l2.a * l1.c - l1.a * l2.c) / (l1.a * l2.b - l2.a * l1.b)));
    }

    /**
     * @param p A Point
     * @param l A Line Segment
     * @return Whether the point lies on the line segment
     */
    public static boolean onLineSeg(Point p, Line l) {
        if ((int) getDistance(p, new Point((int) l.x1, (int) l.y1))
                + (int) getDistance(p, new Point((int) l.x2, (int) l.y2)) == (int) getDistance(
                        new Point((int) l.x1, (int) l.y1), new Point((int) l.x2, (int) l.y2)))
            return true;
        return false;
    }

    /**
     * Uses the Pythagorean Theorem to get distance
     * 
     * @param p1 The first Point
     * @param p2 The second Point
     * @return Distance between Points
     */
    public static double getDistance(Point p1, Point p2) {
        return Math.sqrt((p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y));
    }

    /**
     * 
     * @param l1 First Line
     * @param l2 Second Line
     * @return whether the two Lines have collided
     */
    public static boolean collided(Line l1, Line l2) {
        double collx = (l1.b * l2.c - l2.b * l1.c) / (l1.a * l2.b - l2.a * l1.b);
        double colly = (l2.a * l1.c - l1.a * l2.c) / (l1.a * l2.b - l2.a * l1.b);
        Point p = new Point((int) collx, (int) colly);

        if (onLineSeg(p, l1) && onLineSeg(p, l2)) {
            return true;
        }
        return false;
    }

    /**
     * This method checks if the robot has collided by checking if the edges of the
     * robot have collided with the edges of the field and obstacles.
     * 
     * @return whether the robot has collided
     */
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

    // Calculates the bounding points for collision detection
    @Override
    public void run() {

        while (true) {

            CollisionTracker.getBoundingPoints();

            // Collision logic : What the robot does after it collides?
            if (CollisionTracker.robotCollided()) {
                //System.out.println("collided");
                // Terminates the current running Command and stops the robot from moving
                RunRobot.currCommand.end();

                DriveBase.runLeftSideDrive(0);
                DriveBase.runRightSideDrive(0);

                // Resets the Joystick Inputs
                if (RunRobot.display.axis1.get('y') != 0) {
                    RunRobot.display.axis1.setX(0);
                    RunRobot.display.axis1.setY(0);
                    RunRobot.display.axis2.setX(0);
                    RunRobot.display.axis2.setY(0);
                }

            }
        }
    }
}
