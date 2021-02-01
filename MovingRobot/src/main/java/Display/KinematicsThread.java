package Display;

import Constraints.CollisionTracker;
import MainCode.Subsystems.DriveBase;

public class KinematicsThread extends Thread {

    @Override
    public void run() {
        try {
            sleep(1000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        while (!isInterrupted()) {

            try {
                sleep(50);

                if (!RunRobot.robot.equals(null)) {
                    RunRobot.robot.calcKinematics();
                    // System.out.println("ANGLE: " + Robot.theta + " XPos: "
                    //         + Robot.posX + " YPos: " + Robot.posY);
                    CollisionTracker.getBoundingPoints();

                    //System.out.println(CollisionTracker.RobotBounds[0]);

                    if(CollisionTracker.robotCollided()){
                        //Robot.theta *= -1;
                        DriveBase.runLeftSideDrive(0);
                        DriveBase.runRightSideDrive(0);

                        if(RunRobot.display.axis1.get('y') != 0){
                            RunRobot.display.axis1.setX(0);
                            RunRobot.display.axis1.setY(0);
                            RunRobot.display.axis2.setX(0);
                            RunRobot.display.axis2.setY(0);
                            //System.err.println("oooof");
                        }
                    }

                    RunRobot.display.b.repaint();
                    RunRobot.display.j1.repaint();
                    RunRobot.display.j2.repaint();
                    RunRobot.display.repaint();


                } else {
                    System.out.println("DOUBLE YEET");
                }

            } catch (InterruptedException e) {
                System.out.println("Calculations Thread STOPPED.");
            }
        }
    }
}
