package Background;

import java.util.ArrayList;

import Background.Graphics.Display;
import Background.Graphics.RepaintThread;
import Background.PhysicsEngine.CollisionTracker;
import Background.PhysicsEngine.KinematicsThread;
import Background.PhysicsEngine.Obstacle;
import Background.PhysicsEngine.Point;
import Background.Robot.Robot;
import Background.Robot.Components.Command;
import Background.Robot.Components.Motor;
import Background.Game.Game;

public class RunRobot {

    public static Robot robot;
    public static boolean keyPressed = false;
    public static final int TIME_INTERVAL = 1000;
    public static Display display;
    public static ArrayList<Motor> motors;
    public static MainCode.Robot timedRobot;
    public static ArrayList<Obstacle> obsList;
    public static ArrayList<Point> pointList;
    public static Command currCommand;
    public static Game currGame;

    /**
     * Creates a new Robot.
     */
    public RunRobot() {
        CollisionTracker.setFieldLines();
        obsList = new ArrayList<Obstacle>();
        obsList.add(Obstacle.getObs1());

        RunRobot.robot = new Robot();
        System.out.println("Robot created. Starting new Thread...");

        System.out.println("Creating Display...");
        display = new Display();

        System.out.println("Starting Kinematics Thread...");
        new KinematicsThread().start();

        System.out.println("Starting Collision Tracking Thread...");
        new CollisionTracker().start();

        System.out.println("Starting Repaint Thread...");
        new RepaintThread().start();
        System.out.println("Robot Successfully Initalized...");

    }

}
