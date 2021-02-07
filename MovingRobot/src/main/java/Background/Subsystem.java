package Background;

public class Subsystem {

    public Command currentCommand;

    public Subsystem() {
        System.out.println("SUBSYSTEM CREATED!");
    }

    public void initDefaultCommand() {
        new Command();
    }
}
