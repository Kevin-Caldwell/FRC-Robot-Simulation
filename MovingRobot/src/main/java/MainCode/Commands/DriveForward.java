package MainCode.Commands;

import Background.Robot.Components.Command;
import MainCode.Robot;
import MainCode.Subsystems.DriveBase;

public class DriveForward extends Command {
    public DriveForward() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        super();
        System.out.println("superclass called");
        initialize();
    }

    // Called just before this Command runs the first time
    public void initialize() {
        System.out.println("Tank Drive initialized...");

        super.initialize();
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    public void execute() {
        // System.out.println("Running execute");
        double inputL = 1;
        double inputR = 1;

        //System.out.println(Robot.distanceReader.getDistance() + " : "
          //      + (Robot.distanceReader.getDistance() <= 75 + Background.Robot.ROBOT_LENGTH / 2));

        //System.out.println(Robot.distanceReader.getDistance());

        DriveBase.runLeftSideDrive(inputL);
        DriveBase.runRightSideDrive(inputR);
    }

    // Make this return true when this Command no longer needs to run execute()
    public boolean isFinished() {
        return Robot.encoder.distance > 50;
    }

    // Called once after isFinished returns true
    public void end() {
        new TurnCommand(Math.PI / 2);
        Robot.encoder.resetDistance();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    public void interrupted() {
    }
}
