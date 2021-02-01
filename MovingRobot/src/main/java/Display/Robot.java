package Display;

import java.util.ArrayList;

public class Robot {

    public static double posX = 100;
    public static double posY = 100;
    public static double theta = 0.0;

    public static double speed = 0;
    public static double acceleration = 0;
    public final double TIME_INTERVAL = 0.05;
    public final double MAX_SPEED = 1;
    public final int ROBOT_LENGTH = 60;
    public final int ROBOT_WIDTH = 30;
    public Command currentCommand;

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

        //System.out.println("Net Speed: " + (lTor + rTor));
        int k = 30;

        //System.out.println("ltor: " + lTor + " : " + "rTor: " + rTor);

        theta -= (lTor - rTor) * TIME_INTERVAL * 0.25;

        // translational Kinematics
        posX += Math.cos(theta) * TIME_INTERVAL * (lTor + rTor) * k;
        posY += Math.sin(theta) * TIME_INTERVAL * (lTor + rTor) * k;
    }
}
