package MainCode.Commands;

import Display.Command;
import MainCode.Subsystems.DriveBase;

public class DoNothing extends Command {
    public DoNothing() {
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
    public void execute() {
        // System.out.println("Running execute");
        double inputL = 0;
        double inputR = 0;

        DriveBase.runLeftSideDrive(inputL);
        DriveBase.runRightSideDrive(inputR);
    }

    // Make this return true when this Command no longer needs to run execute()
    public boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    public void end() {

    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    public void interrupted() {
    }
}
