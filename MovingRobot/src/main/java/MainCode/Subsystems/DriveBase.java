package MainCode.Subsystems;

import Background.Motor;
import Background.RunRobot;
import Background.Subsystem;
import MainCode.Commands.TankDrive;

public class DriveBase extends Subsystem {

    public static Motor leftDriveFront = new Motor(-1);
    public static Motor leftDriveMiddle = new Motor(-1);
    public static Motor leftDriveBack = new Motor(-1);
    public static Motor rightDriveFront = new Motor(1);
    public static Motor rightDriveMiddle = new Motor(1);
    public static Motor rightDriveBack = new Motor(1);

    public boolean reverse = false;

    public DriveBase() {
        RunRobot.motors.add(leftDriveFront);
        RunRobot.motors.add(leftDriveMiddle);
        RunRobot.motors.add(leftDriveBack);

        RunRobot.motors.add(rightDriveFront);
        RunRobot.motors.add(rightDriveMiddle);
        RunRobot.motors.add(rightDriveBack);

        System.out.println("Motors initialized in DriveBase... " + RunRobot.motors);

        initDefaultCommand();
    }

    public static void runLeftSideDrive(double leftDriveStick) {
        // System.out.println("runnig leftsidedrive");

        leftDriveFront.set(leftDriveStick);
        leftDriveMiddle.set(leftDriveStick);
        leftDriveBack.set(leftDriveStick);

        // Runs left drive
    }

    public static void runRightSideDrive(double rightDriveStick) {
        // System.out.println("runnig rightsidedrive");

        rightDriveFront.set(rightDriveStick);
        rightDriveMiddle.set(rightDriveStick);
        rightDriveBack.set(rightDriveStick);

        // Runs right drive
    }

    // initiate TankDrive
    @Override
    public void initDefaultCommand() {
        System.out.println("Calling Default Command...");
        new TankDrive();
    }
}
