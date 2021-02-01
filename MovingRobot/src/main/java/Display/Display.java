package Display;

import javax.swing.JButton;
import javax.swing.JFrame;

//import Constraints.Field;

import java.awt.Dimension;

public class Display extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = -5224886870773903429L;

    public KeyControls kc;
    public RobotPaint b;

    public JoystickPanel j1;
    public JoystickPanel j2;
    public Joystick axis1 = new Joystick();
    public Joystick axis2 = new Joystick();

    public int x = 0;
    public int y = 0;

    public boolean obstacleMode = false;
    public JButton addObstacle;

    public Display() {
        System.out.println("Starting Display...");

        //addMouseListener(new MouseTracker());

        b = new RobotPaint();
        b.setBounds(0, 0, 500, 500);

        kc = new KeyControls();
        b.addKeyListener(kc);
        b.addMouseListener(new MouseTracker());

        j1 = new JoystickPanel(axis1);
        j1.setBounds(500, 0, 100, 100);
        j1.addMouseListener(new PointerTracker1());

        j2 = new JoystickPanel(axis2);
        j2.setBounds(600, 0, 100, 100);
        j2.addMouseListener(new PointerTracker2());

        addObstacle = new JButton("Add obstacles");
        addObstacle.setBounds(500, 120, 150, 50);
        addObstacle.addActionListener(new AddObstacle());

        add(b);
        add(j1);
        add(j2);
        add(addObstacle);

        //setExtendedState(JFrame.MAXIMIZED_BOTH);
        //setUndecorated(true);
        //setVisible(true);
        setSize(new Dimension(750, 750));
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        System.out.println("Canvas has focus: " + j1.requestFocusInWindow());
        repaint();
    }
}
