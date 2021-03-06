package Background.Graphics;

//import Field;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.Rectangle;

import javax.swing.JPanel;

import Background.RunRobot;
import Background.Game.GameConstraints.FieldConstraints;
import Background.PhysicsEngine.CollisionTracker;
import Background.PhysicsEngine.Line;
import Background.PhysicsEngine.Obstacle;
import Background.PhysicsEngine.Point;
import Background.Robot.Robot;

/**
 * JPanel responsible for drawing the Robot, Obstacles and other debug features.
 * 
 * @author Kevin Caldwell
 */
public class RobotPaint extends JPanel {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public boolean showObstacles = true;
    public boolean showRobot = true;
    public boolean dirVecs = true;
    public boolean boundary = false;
    public boolean intersection = false;
    public boolean track = false;

    public static boolean auto = false;
    public static boolean teleop = false;

    public RobotPaint() {
        super();
        System.out.println("Graphics initialized...");
    }

    @Override
    public void paintComponent(java.awt.Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        // g.clearRect(0, 0, 100, 100);
        SetUpField(g2d);

        g.setColor(Color.BLUE);

        if (RunRobot.robot != null) {
            if (showObstacles)
                PaintObstacles(g2d);
            if (showRobot)
                //paintRobot(g2d);
                altRobotPaint(g2d);
            if (dirVecs)
                paintDirectionVector(g2d);
            if (boundary)
                ShowBoundaryPoints(g2d);
            if (intersection)
                ShowIntersection(g2d);
            if (track)
                DrawTracker(g2d);

            paintUltrasonics(g2d);
            // DrawBalls(g2d);
        } else {
            System.out.println("YEET");
        }
    }

    /**
     * DO NOT USE
     * 
     * @param g2d
     */
    public void DrawTracker(Graphics2D g2d) {
        g2d.drawRect(RunRobot.display.x, RunRobot.display.y, 50, 25);
        String str = Integer.toString(RunRobot.display.x) + " : " + Integer.toString(RunRobot.display.y);
        g2d.drawString(str, RunRobot.display.x, RunRobot.display.y);
    }

    /**
     * Draws the robot by drawing a rectangle with center ({@code posX},
     * {@code posY}) and then rotating it by an angle of {@code theta}
     */
    public void paintRobot(Graphics2D g2d) {
        if(auto){
            g2d.setColor(Color.GREEN);
        } else if(teleop){
            g2d.setColor(Color.BLUE);
        } else {
            g2d.setColor(Color.MAGENTA);
        }

        Rectangle rect2 = new Rectangle((int) Robot.posX - Robot.ROBOT_LENGTH / 2,
                (int) Robot.posY - Robot.ROBOT_WIDTH / 2, Robot.ROBOT_LENGTH, Robot.ROBOT_WIDTH);
        g2d.rotate(Robot.theta * 1, (int) Robot.posX, (int) Robot.posY);
        g2d.draw(rect2);
        g2d.rotate(Robot.theta * -1, (int) Robot.posX, (int) Robot.posY);

    }

    /**
     * Draws a vector displaying the direction the Robot is facing.
     * 
     * @param g2d
     */
    public void paintDirectionVector(Graphics2D g2d) {
        g2d.rotate(Robot.theta, (int) Robot.posX, (int) Robot.posY);
        int length = 75;
        g2d.setColor(Color.GREEN);// 1.009

        g2d.drawLine((int) Robot.posX, (int) Robot.posY, (int) Robot.posX + length, (int) Robot.posY);
        g2d.rotate(-1 * Robot.theta, (int) Robot.posX, (int) Robot.posY);
    }
    /**
     * Does not function at the moment.
     */
    public void paintUltrasonics(Graphics2D g2d) {
        g2d.setColor(Color.CYAN);
        int length = 100;
        if (Robot.ultrasonics != null) {

            for (Background.Robot.Sensors.Ultrasonic u : Robot.ultrasonics) {

                // System.out.print(u.getDistance() + "\t\t");
                g2d.drawLine((int) Robot.posX, (int) Robot.posY,
                        (int) Robot.posX + (int) (length * Math.cos(Robot.theta + u.angle)),
                        (int) Robot.posY + (int) (length * Math.sin(Robot.theta + u.angle)));
            }
            // System.out.println();
        }
        g2d.setColor(Color.green);
    }

    /**
     * Draws a thick line along the boundaries of the Field
     * 
     * @param g2d
     */
    public void SetUpField(Graphics2D g2d) {
        g2d.setStroke(new BasicStroke(3));
        g2d.drawRect(FieldConstraints.UPPER_LEFT, FieldConstraints.UPPER_LEFT, FieldConstraints.WIDTH - 1,
                FieldConstraints.HEIGHT - 1);
        g2d.setStroke(new BasicStroke(1));
    }

    /**
     * Draws a circle of radius 10 pixels around the Boundary points of the robot
     * 
     * @param g2d
     */
    public void ShowBoundaryPoints(Graphics2D g2d) {
        int r = 10;

        g2d.drawOval((int) CollisionTracker.RobotBounds[0].x - r / 2, (int) CollisionTracker.RobotBounds[0].y - r / 2,
                r, r);
        g2d.drawOval((int) CollisionTracker.RobotBounds[1].x - r / 2, (int) CollisionTracker.RobotBounds[1].y - r / 2,
                r, r);
        g2d.drawOval((int) CollisionTracker.RobotBounds[2].x - r / 2, (int) CollisionTracker.RobotBounds[2].y - r / 2,
                r, r);
        g2d.drawOval((int) CollisionTracker.RobotBounds[3].x - r / 2, (int) CollisionTracker.RobotBounds[3].y - r / 2,
                r, r);
    }

    /**
     * Draws a circle of radius 10 pixels on the points of intersection of of the
     * Lines of the Robot and the Field
     * 
     * @param g2d
     */
    public void ShowIntersection(Graphics2D g2d) {
        int r = 10;

        for (Background.PhysicsEngine.Line l1 : CollisionTracker.RobotLines) {
            for (Background.PhysicsEngine.Line l2 : CollisionTracker.FieldLines) {
                Point p1 = CollisionTracker.GetPointOfIntersection(l1, l2);
                g2d.setColor(Color.blue);

                g2d.drawOval((int) p1.x - r / 2, (int) p1.y - r / 2, r, r);
                g2d.drawLine((int) p1.x, (int) p1.y, (int) Robot.posX, (int) Robot.posY);
            }
        }

        // for(Obstacle obs : RunRobot.obsList){
        // for (Line l1 : CollisionTracker.RobotLines) {
        // for (Line l2 : obs.lines) {
        // Point p1 = CollisionTracker.GetPointOfIntersection(l1, l2);
        // g2d.setColor(Color.blue);

        // g2d.drawOval((int) p1.x - r / 2, (int) p1.y - r / 2, r, r);
        // g2d.drawLine(p1.x, p1.y, (int) Robot.posX, (int) Robot.posY);
        // }
        // }

        // }
    }

    public void altRobotPaint(Graphics2D g2d) {
        if(auto){
            g2d.setColor(Color.GREEN);
        } else if(teleop){
            g2d.setColor(Color.BLUE);
        } else {
            g2d.setColor(Color.MAGENTA);
        }

        CollisionTracker.getBoundingPoints();
        if (CollisionTracker.RobotLines != null) {
            for (Line l : CollisionTracker.RobotLines) {
                g2d.drawLine((int) l.x1, (int) l.y1, (int) l.x2, (int) l.y2);
            }
        }
    }

    /**
     * Draws all obstacles found in {@code RunRobot.obsList}
     * 
     * @param g2d
     */
    public void PaintObstacles(Graphics2D g2d) {
        g2d.setColor(Color.GRAY);
        if (RunRobot.obsList != null) {
            for (Obstacle obstacle : RunRobot.obsList) {
                for (Line line : obstacle.lines) {
                    // System.out.println("hi");
                    g2d.drawLine((int) line.x1, (int) line.y1, (int) line.x2, (int) line.y2);
                }
            }
        }
    }
}