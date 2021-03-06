package Background.Robot;

import java.util.ArrayList;

import Background.RunRobot;
import Background.Robot.Sensors.Ultrasonic;
import Background.Robot.Components.Command;
import Background.Robot.Components.Motor;

public class Robot {

    public static double posX = 100;
    public static double posY = 100;
    public static double theta = 0.0;

    public static double speed = 0;
    public static double acceleration = 0;
    public final double TIME_INTERVAL = 0.001;
    public final double MAX_SPEED = 1;
    public static final int ROBOT_LENGTH = 60;
    public static final int ROBOT_WIDTH = 30;

    public static double START_X = 100;
    public static double START_Y = 100;
    public static double START_THETA = 0;
    public Command currentCommand;
    public static ArrayList<Ultrasonic> ultrasonics;

    public Robot() {

        currentCommand = new Command();

        RunRobot.motors = new ArrayList<Motor>();
        System.out.println("Motors ArrayList has been created. Initiallizing MainCode Robot...");

        RunRobot.timedRobot = new MainCode.Robot();
        System.out.println("Motors initialized...");
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
        double ANGLE_k = 2 / (1);
        double POS_k = 30 / (1);

        // Rotational Kkinematics
        theta -= (lTor - rTor) * TIME_INTERVAL / ANGLE_k;

        // Translational Kinematics
        double deltaX = Math.cos(theta) * TIME_INTERVAL * (lTor + rTor) * POS_k;
        double deltaY = Math.sin(theta) * TIME_INTERVAL * (lTor + rTor) * POS_k;

        MainCode.Robot.encoder.updateDistance(Math.sqrt(deltaX * deltaX + deltaY * deltaY));

        posX += deltaX;
        posY += deltaY;
    }
}
