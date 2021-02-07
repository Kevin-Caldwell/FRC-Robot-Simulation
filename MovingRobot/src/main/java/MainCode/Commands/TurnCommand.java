package MainCode.Commands;

import Background.Command;
import MainCode.Robot;
import MainCode.Subsystems.DriveBase;

public class TurnCommand extends Command{
    double initialAngle;
    public TurnCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        super();
        System.out.println("superclass called");
        initialAngle = Robot.angleReader.getAngle();
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
        System.err.println(Robot.angleReader.getAngle());
        if(Math.abs(Robot.angleReader.getAngle() - initialAngle) >= Math.PI / 4){
            end();
            
        }

        double inputL = -1;
        double inputR = 1;

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
