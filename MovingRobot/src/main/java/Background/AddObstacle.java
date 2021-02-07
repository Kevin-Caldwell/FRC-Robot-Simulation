package Background;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import GameConstraints.Obstacle;

import java.awt.Point;

public class AddObstacle implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        if (RunRobot.display.obstacleMode) {
            RunRobot.obsList.add(new Obstacle(RunRobot.pointList));
            RunRobot.pointList = new ArrayList<Point>();
        } else {
            RunRobot.pointList = new ArrayList<Point>();
        }

        RunRobot.display.obstacleMode = !RunRobot.display.obstacleMode;
        RunRobot.display.addObstacle.setText(RunRobot.display.obstacleMode ? "Exit Obstacle Mode" : "Add obstacles");
        System.out.println("it works : " + RunRobot.display.obstacleMode);

    }

}
