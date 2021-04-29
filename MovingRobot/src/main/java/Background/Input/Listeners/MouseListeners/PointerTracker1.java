package Background.Input.Listeners.MouseListeners;

import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputListener;

import Background.RunRobot;

public class PointerTracker1 implements MouseInputListener {

    @Override
    public void mouseClicked(MouseEvent e) {
        // System.out.println("Clicked" + e.getPoint());

    }

    @Override
    public void mousePressed(MouseEvent e) {
        // System.out.println("Pressed" + e.getPoint());
        RunRobot.display.axis1.setX(50 - e.getX());
        RunRobot.display.axis1.setY(50 - e.getY());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // System.out.println("Released" + e.getPoint());

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // System.out.println("Entered" + e.getPoint());

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // System.out.println("Exited" + e.getPoint());
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // System.out.println("Dragged" + e.getPoint());
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // System.out.println("Moved" + e.getPoint());
    }

}
