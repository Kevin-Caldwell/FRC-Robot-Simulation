package Background;

public class Command implements Runnable {

    protected boolean isRunning = false;
    private Thread t;

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

    @Override
    public void run() {
        System.out.println("Robot Thread started\n");
        while (isRunning) {
            execute();
            // System.out.println("HALLO?");
            try {
                // System.out.println(isRunning);
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Command ended");
    }
}
