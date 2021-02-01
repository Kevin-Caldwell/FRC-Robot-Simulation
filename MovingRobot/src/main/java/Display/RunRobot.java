package Display;

import java.util.ArrayList;

import Constraints.CollisionTracker;
import Constraints.Obstacle;

public class RunRobot {

    public static Robot robot;
    public static boolean keyPressed = false;
    public static final int TIME_INTERVAL = 1000;
    public static Display display;
    public static ArrayList<Motor> motors;
    public static MainCode.Robot timedRobot;
    public static ArrayList<Obstacle> obsList;

    public RunRobot(){
        CollisionTracker.setFieldLines();
        obsList = new ArrayList<Obstacle>();
        obsList.add(Obstacle.getObs1());

        System.out.println("Creating Display...");
        
        System.out.println("Display Initialized. initializing Display.Robot...");
        RunRobot.robot = new Robot();
        System.out.println("Robot created. Starting new Thread...");
        display = new Display();
        System.out.println("Robot Successfully Initalized...");

    }
    
}