package Background;

import GameConstraints.CollisionTracker;
import MainCode.Subsystems.DriveBase;

/**
 * Thread which controls the Kinematics of the Robot.
 * 
 * @author Kevin Caldwell
 */
public class KinematicsThread extends Thread {

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
                sleep(5);

                if (!RunRobot.robot.equals(null)) {
                    RunRobot.robot.calcKinematics(); // Method calculates Robot's position vector

                    // System.out.println("ANGLE: " + Robot.theta + " XPos: "
                    // + Robot.posX + " YPos: " + Robot.posY);

                    // Calculates the bounding points for collision detection
                    CollisionTracker.getBoundingPoints(); 

                    // Collision logic : What the robot does after it collides?
                    if (CollisionTracker.robotCollided()) {
                        // Terminates the current running Command and stops the robot from moving
                        RunRobot.currCommand.isRunning = false;
                        
                        DriveBase.runLeftSideDrive(0);
                        DriveBase.runRightSideDrive(0);

                        // Resets the Joystick Inputs
                        if (RunRobot.display.axis1.get('y') != 0) {
                            RunRobot.display.axis1.setX(0);
                            RunRobot.display.axis1.setY(0);
                            RunRobot.display.axis2.setX(0);
                            RunRobot.display.axis2.setY(0);
                        }
                    }

                    // Draws all the components of the Display.
                    if (RunRobot.display != null) {
                        RunRobot.display.b.repaint();
                        RunRobot.display.j1.repaint();
                        RunRobot.display.j2.repaint();
                        RunRobot.display.repaint();
                    }

                } else {
                    
                }

            } catch (InterruptedException e) {
                System.out.println("Calculations Thread STOPPED.");
            }
        }
    }
}
