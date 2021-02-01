package Display;

public class Command implements Runnable {

    protected boolean isRunning = false;
    private Thread t;

    public Command() {
        isRunning = true;
    }

    public void initialize() {

        isRunning = true;
        t = new Thread(this, "Command Thread");
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
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    }
}
