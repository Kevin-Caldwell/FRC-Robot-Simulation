package Background.Game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import Background.RunRobot;
import Background.PhysicsEngine.Obstacle;
import Background.PhysicsEngine.Point;

/**
 * ActionListener for Switching between Drive Mode and Obstacle Mode.
 * <p>Drive mode allows user to interact with the Robot.</p>
 * <p>Obstacle Mode allows user to add more obstacles to the field.</p>
 * @author Kevin Caldwell
 */
public class AddObstacle implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        if (RunRobot.display.obstacleMode) {
            if (!RunRobot.pointList.isEmpty()) {
                RunRobot.obsList.add(new Obstacle(RunRobot.pointList));
            }
            RunRobot.pointList = new ArrayList<Point>();
        } else {
            RunRobot.pointList = new ArrayList<Point>();
        }

        RunRobot.display.obstacleMode = !RunRobot.display.obstacleMode;
        RunRobot.display.addObstacle.setText(RunRobot.display.obstacleMode ? "Exit Obstacle Mode" : "Add obstacles");

    }

}
