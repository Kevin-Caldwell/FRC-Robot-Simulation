package Background.Robot.Sensors;

import Background.RunRobot;
import Background.PhysicsEngine.CollisionTracker;
import Background.PhysicsEngine.Line;
import Background.PhysicsEngine.Obstacle;
import Background.PhysicsEngine.Point;
import Background.Robot.Robot;

import java.util.ArrayList;

public class Ultrasonic {
    public double angle;

    public Ultrasonic(double angle) {
        this.angle = angle;
        if (Robot.ultrasonics != null) {
            Robot.ultrasonics.add(this);
        } else {
            Robot.ultrasonics = new ArrayList<Ultrasonic>();
            Robot.ultrasonics.add(this);
        }
    }

    public double getDistance() {
        int i = 10000;
        Point p = new Point((int) (Robot.posX + i * Math.cos(Robot.theta + angle)),
                (int) (Robot.posY + i * Math.sin(Robot.theta + angle)));
        Line l = new Line(new Point((int) Robot.posX, (int) Robot.posY), p);

        double distance = 1000;

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
