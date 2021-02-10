package Background;

import java.awt.Color;
import java.awt.Rectangle;

import javax.swing.JPanel;

/**
 * JPanel for Displaying Joysticks
 * @author Kevin Caldwell
 * 
 */
public class JoystickPanel extends JPanel {

    /**
     *
     */
    private static final long serialVersionUID = -7268791957598835508L;

    public Joystick j = new Joystick();
    private final int PANEL_SIDE = 100;

    public JoystickPanel(Joystick j) {
        this.j = j;
    }

    @Override
    public void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);
        // System.out.println(getBounds());

        Rectangle r = getBounds();
        g.clearRect(r.x, r.y, r.width, r.height);
        // g.fillRect(0, 0, 100, 100);
        // g.fillOval(0, 0, PANEL_SIDE, PANEL_SIDE);
        drawJoystick(g);
    }

    private void drawJoystick(java.awt.Graphics g) {
        g.setColor(Color.CYAN);
        g.fillOval(0, 0, PANEL_SIDE, PANEL_SIDE);
        g.setColor(Color.BLACK);
        g.fillOval(50 - (int) j.get('x') - 5, 50 - (int) j.get('y') - 5, 10, 10);

    }
}
