package Background.Robot.Components;

public class Subsystem {

    public Command currentCommand;

    public Subsystem() {
        System.out.println("SUBSYSTEM CREATED!");
    }

    public void initDefaultCommand() {
        new Command();
    }
}
