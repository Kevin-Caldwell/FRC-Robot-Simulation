package MainCode.Commands;

import Background.Robot.Components.Command;
import MainCode.Robot;
import MainCode.Subsystems.DriveBase;

public class TurnCommand extends Command{
    double initialAngle;
    double angle;

    public TurnCommand(double angle) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        super();
        System.out.println("superclass called");

        initialAngle = Robot.angleReader.getAngle();
        this.angle = angle;

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
        
        double inputL = -1;
        double inputR = 1;
        
        //.println(Robot.angleReader.getAngle() - initialAngle);
        
        DriveBase.runLeftSideDrive(inputL);
        DriveBase.runRightSideDrive(inputR);
    }
    
    // Make this return true when this Command no longer needs to run execute()
    public boolean isFinished() {
        return (Math.abs(Robot.angleReader.getAngle() - initialAngle) >= angle);
    }
    
    // Called once after isFinished returns true
    public void end() {
        new DriveForward();
    }
    
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    public void interrupted() {
    }   
}
