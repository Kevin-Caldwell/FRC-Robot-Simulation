package Background;

import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputListener;
import GameConstraints.Point;

/**
 * MouseInputListener for adding Points in Obstacle Mode.
 */
public class MouseTracker implements MouseInputListener {

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (RunRobot.display.obstacleMode) {
            RunRobot.pointList.add(new Point(e.getX(), e.getY()));
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

}
