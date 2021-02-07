package Background.Sensors;

import Background.Robot;
import Background.RunRobot;
import GameConstraints.CollisionTracker;
import GameConstraints.Line;
import GameConstraints.Obstacle;

import java.awt.Point;

public class Ultrasonic {
    public double angle;

    public Ultrasonic(double angle) {
        this.angle = angle;
        if (Robot.ultrasonics != null) {
            Robot.ultrasonics.add(this);
        }
    }

    public double getDistance() {
        Point p = new Point((int) (Robot.posX + Robot.ROBOT_LENGTH / 2 * Math.cos(Robot.theta + angle)),
                (int) (Robot.posY + Robot.ROBOT_LENGTH / 2 * Math.sin(Robot.theta + angle)));
        Line l = new Line(new Point((int) Robot.posX, (int) Robot.posY), p);

        double distance = 214;

        for (Line l2 : CollisionTracker.FieldLines) {
            Point intersect = CollisionTracker.GetPointOfIntersection(l, l2);
            double temp = (CollisionTracker.getDistance(intersect, new Point((int) Robot.posX, (int) Robot.posY)));
            if (CollisionTracker.onLineSeg(intersect, l2) && CollisionTracker.onLineSeg(intersect, l)) {
                distance = temp < distance ? temp : distance;
            }
            // System.out.println(temp);
        }

        for (Obstacle obs : RunRobot.obsList) {
            for (Line l2 : obs.lines) {
                Point intersect = CollisionTracker.GetPointOfIntersection(l, l2);
                double temp = (CollisionTracker.getDistance(intersect, new Point((int) Robot.posX, (int) Robot.posY)));
                if (CollisionTracker.onLineSeg(intersect, l2) && CollisionTracker.onLineSeg(intersect, l)) {
                    distance = temp < distance ? temp : distance;
                }
                // System.out.println(temp + " : " + l2.x1 + " : " + l2.y1);
            }
        }
        return distance;
    }

}
