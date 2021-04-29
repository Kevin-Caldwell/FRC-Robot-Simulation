package Background.PhysicsEngine;

import Background.RunRobot;

/**
 * Thread which controls the Kinematics of the Robot.
 * 
 * @author Kevin Caldwell
 */
public class KinematicsThread extends Thread {

    public KinematicsThread(){
        this.setName("Kinematics Thread");
    }

    @Override
    public void run() {

        // Thread waiting for other components to initialize
        try {
            sleep(1000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }

        // Loop runs every 5 ms.
        while (!isInterrupted()) {
            try {
                sleep(1);
                if (!RunRobot.robot.equals(null)) {
                    RunRobot.robot.calcKinematics(); // Method calculates Robot's position vector
                }
            } catch (InterruptedException e) {
                System.out.println("Calculations Thread STOPPED.");
            }
        }
    }
}
