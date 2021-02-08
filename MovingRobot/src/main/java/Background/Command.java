package Background;

/**
 * Command superclass for all Commands. While {@code isRunning} is {@code true},
 * the Command runs in a separate Thread. Once {@code isRunning} is
 * {@code false}, the Thread terminates. When the command is initialized, the
 * {@code currCommand} is terminated and this Command takes its place. 
 * @author Kevin Caldwell
 */
public class Command implements Runnable {

    protected boolean isRunning = false;
    private Thread t;

    /**
     * Creates a new Command and starts a new Command Thread
     */
    public Command() {
        isRunning = true;

        if (RunRobot.currCommand != null) {
            System.out.println("Command already running");
            RunRobot.currCommand.isRunning = false;
            System.out.println("the command should've ended");
        }

        RunRobot.currCommand = this;
    }

    public void initialize() {

        isRunning = true;
        t = new Thread(this, this.getClass().getSimpleName() + " Command Thread");
        t.start();
    }

    /**
     * While the command is running, execute is called every 20 ms.
     */
    public void execute() {
        System.out.println("execute");
    }

    public void end() {
        isRunning = false;
    }

    public void interrupted() {
        isRunning = false;
        end();
    }

    /**
     * Execute is called every 20 ms.
     */
    @Override
    public void run() {
        System.out.println("Command Thread started\n");
        while (isRunning) {
            execute();
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Command ended");
    }
}
