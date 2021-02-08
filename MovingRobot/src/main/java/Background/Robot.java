package Background;

import java.util.ArrayList;

import Background.Sensors.Ultrasonic;

public class Robot {

    public static double posX = 100;
    public static double posY = 100;
    public static double theta = 0.0;

    public static double speed = 0;
    public static double acceleration = 0;
    public final double TIME_INTERVAL = 0.005;
    public final double MAX_SPEED = 1;
    public static final int ROBOT_LENGTH = 60;
    public static final int ROBOT_WIDTH = 30;

    public static int START_X = 100;
    public static int START_Y = 100;
    public static double START_THETA = 0;
    public Command currentCommand;
    public static ArrayList<Ultrasonic> ultrasonics;

    public Robot() {

        currentCommand = new Command();

        RunRobot.motors = new ArrayList<Motor>();
        System.out.println("Motors ArrayList has been created. Initiallizing MainCode Robot...");

        RunRobot.timedRobot = new MainCode.Robot();
        System.out.println("Motors initialized...");

        new KinematicsThread().start();
    }

    public void calcKinematics() {

        // rotational kinematics

        double lTor = 0;
        double rTor = 0;

        for (Motor motor : RunRobot.motors) {
            if (motor.DeviceNumber == -1)
                lTor += motor.getSpeed();
            if (motor.DeviceNumber == 1)
                rTor += motor.getSpeed();
        }

        // System.out.println("Net Speed: " + (lTor + rTor));
        // Constants for controlling Angular and Linear Velocity
        int ANGLE_k = 2;
        int POS_k = 30;

        // Rotational Kkinematics
        theta -= (lTor - rTor) * TIME_INTERVAL / ANGLE_k;

        // Translational Kinematics
        posX += Math.cos(theta) * TIME_INTERVAL * (lTor + rTor) * POS_k;
        posY += Math.sin(theta) * TIME_INTERVAL * (lTor + rTor) * POS_k;
    }
}
