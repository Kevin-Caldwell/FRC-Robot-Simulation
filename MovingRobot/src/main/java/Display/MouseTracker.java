package Display;

import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputListener;

public class MouseTracker implements MouseInputListener {

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("MOVED1");
        RunRobot.display.x = e.getX();
        RunRobot.display.y = e.getY();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("MOVED");
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
