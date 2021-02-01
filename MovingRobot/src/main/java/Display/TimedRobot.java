package Display;

public class TimedRobot implements Runnable{
    
    public TimedRobot(){
        
        }

    public void robotInit(){

    }

    public void robotPeriodic() {}

    public void disabledInit() {}

    public void disabledPeriodic() {}

    public void autonomousInit() {

    }

    public void autonomousPeriodic() {}

    public void teleopInit() {}

    public void teleopPeriodic() {}

    public void testPeriodic() {}

    @Override
    public void run() {
        System.out.println("Robot Thread started\n");
        while (true) {
            autonomousPeriodic();
            //System.out.println("HALLO?");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
        }
    }

}
