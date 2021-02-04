package GameConstraints;

import Display.RunRobot;
import MainCode.Robot;

public class Game extends Thread {
    public static int AUTO_TIME = 15000;
    public static int TELEOP_TIME = 15000;
    public static int ENDGAME = 15000;

    public Game() {
        this.start();
    }

    public void runAuto() {
        System.out.println("Running Autonomous Period");
        RunRobot.timedRobot.autonomousInit();
        Timer t = new Timer();
        t.start();
        while (t.getTimeElapsed() <= AUTO_TIME) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            RunRobot.timedRobot.autonomousPeriodic();
        }
    }

    public void runTeleop() {
        System.out.println("Running Teleop Period");
        RunRobot.timedRobot.teleopInit();
        Timer t = new Timer();
        t.start();
        while (t.getTimeElapsed() <= AUTO_TIME) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            RunRobot.timedRobot.teleopPeriodic();
        }
    }

    @Override
    public void run() {
        RunRobot.timedRobot.robotInit();
        System.out.println("Game has Started");
        runAuto();
        runTeleop();
        System.out.println("Game has Ended");
        Robot.dt.initDefaultCommand();
    }

}
