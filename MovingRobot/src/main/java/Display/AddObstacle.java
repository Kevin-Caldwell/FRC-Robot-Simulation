package Display;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddObstacle implements ActionListener {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Override
    public void actionPerformed(ActionEvent e) {
        RunRobot.display.obstacleMode = !RunRobot.display.obstacleMode;
        RunRobot.display.addObstacle.setText(RunRobot.display.obstacleMode ? "Exit Obstacle Mode" : "Add obstacles");
        System.out.println("it works : " + RunRobot.display.obstacleMode);
    }
    
}
