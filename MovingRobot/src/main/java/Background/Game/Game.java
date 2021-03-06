package Background.Game;

import Background.RunRobot;
import Background.Game.GameConstraints.Timer;
import Background.Graphics.RobotPaint;
import MainCode.Robot;

/**
 * Game class controls the Autonomous and Teleop period of the Simulation Game.
 * Runs as a separate Thread.
 * 
 * @author Kevin Caldwell
 */
public class Game extends Thread {
    /**
     * Duration in Milliseconds of Autonomous period.
     * 
     */
    public static int AUTO_TIME = 15000;

    /**
     * Duration in Milliseconds of Teleop period
     */
    public static int TELEOP_TIME = 15000;

    /**
     * Duration in Milliseconds(ms) of Endgame period
     */
    public static int ENDGAME = 15000;

    private boolean RUNNING = false;

    /**
     * Constuctor starts new Game Thread
     */
    public Game() {
        this.setName("Game Thread");
        this.start();
        if (RunRobot.currGame != null) {
            
        } else if(RunRobot.currGame == null){

        }
        else if (RunRobot.currGame.RUNNING) {
            this.RUNNING = false;
        } else {
            RunRobot.currGame = this;
        }

        if(RunRobot.currGame == null){
            RunRobot.currGame = this;
        } else if(RunRobot.currGame.RUNNING){
            this.RUNNING = false;
        } else {
            RunRobot.currGame = this;
        }
    }

    /**
     * runs Autonomous Period for AUTO_TIME ms and calls
     * {@code RunRobot.timedRobot.autonomousInit()} once and calls
     * {@code RunRobot.timedRobot.autonomousPeriodic()} every 20 ms.
     */
    public void runAuto() {
        if (RUNNING) {
            RunRobot.display.mode.setName("Auto");
            System.out.println("Running Autonomous Period");
            RunRobot.timedRobot.autonomousInit();
            Timer t = new Timer();
            t.start();
            while (t.getTimeElapsed() <= AUTO_TIME && RUNNING) {
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                RunRobot.timedRobot.autonomousPeriodic();
            }
        }
    }

    /**
     * runs Teleop Period for TELEOP_TIME ms and calls
     * {@code RunRobot.timedRobot.teleopInit()} once and calls
     * {@code RunRobot.timedRobot.teleopPeriodic()} every 20 ms.
     */
    public void runTeleop() {
        if (RUNNING) {
            RunRobot.display.mode.setName("Teleop");
            System.out.println("Running Teleop Period");
            RunRobot.timedRobot.teleopInit();
            Timer t = new Timer();
            t.start();
            while (t.getTimeElapsed() <= AUTO_TIME && RUNNING) {
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                RunRobot.timedRobot.teleopPeriodic();
            }
        }
    }

    public void end() {
        RUNNING = false;
    }

    /**
     * Runs the Game. Autonomous is called first, then Teleop. Once the game is
     * finished, Robot.dt.initDefaultCommand() is called.
     */
    @Override
    public void run() {
        RunRobot.timedRobot.robotInit();
        System.out.println("Game has Started");

        RobotPaint.auto = true;
        runAuto();
        RobotPaint.auto = false;

        RobotPaint.teleop = true;
        runTeleop();
        RobotPaint.teleop = false;
        RunRobot.display.mode.setName("Drive Mode");
        System.out.println("Game has Ended");
        Robot.dt.initDefaultCommand();
    }

}
