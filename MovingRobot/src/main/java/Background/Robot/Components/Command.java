package Background.Robot.Components;

import Background.RunRobot;

/**
 * Command superclass for all Commands. While {@code isRunning} is {@code true},
 * the Command runs in a separate Thread. Once {@code isRunning} is
 * {@code false}, the Thread terminates. When the command is initialized, the
 * {@code currCommand} is terminated and this Command takes its place. 
 * @author Kevin Caldwell
 */
public class Command implements Runnable {

    public boolean isRunning = false;
    private Thread t;

    /**
     * Creates a new Command and starts a new Command Thread
     */
    public Command() {
        isRunning = true;

        if (RunRobot.currCommand != null) {
            System.out.println(RunRobot.currCommand.getClass().getSimpleName() + " Command already running");
            RunRobot.currCommand.isRunning = false;
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
    }

    public boolean isFinished(){
        return false;
    }

    public void interrupted() {
        isRunning = false;
    }

    /**
     * Execute is called every 20 ms.
     */
    @Override
    public void run() {
        System.out.println(this.getClass().getSimpleName() + " Command Thread started...");
        while (!isFinished() && isRunning) {
            
            execute();
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        end();

        System.out.println(this.getClass().getSimpleName() + " Command ended");
    }
}
