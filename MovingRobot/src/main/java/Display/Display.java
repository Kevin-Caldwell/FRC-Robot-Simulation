package Display;

import javax.swing.JButton;
import javax.swing.JFrame;

import GameConstraints.Game;

//import Constraints.Field;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

        // addMouseListener(new MouseTracker());

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

        JButton resetButton = new JButton("Reset Position");
        resetButton.setBounds(500, 200, 150, 50);
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Robot.posX = Robot.START_X;
                Robot.posY = Robot.START_Y;
                Robot.theta = Robot.START_THETA;

                RunRobot.display.axis1.setX(0);
                RunRobot.display.axis1.setY(0);
                RunRobot.display.axis2.setX(0);
                RunRobot.display.axis2.setY(0);
            }
        });

        JButton startGame = new JButton("Start Game");
        startGame.setBounds(500, 280, 150, 50);
        startGame.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new Game();
            }
        });

        add(b);
        add(j1);
        add(j2);
        add(addObstacle);
        add(resetButton);
        add(startGame);

        // setExtendedState(JFrame.MAXIMIZED_BOTH);
        // setUndecorated(true);
        // setVisible(true);
        setSize(new Dimension(750, 750));
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        System.out.println("Canvas has focus: " + j1.requestFocusInWindow());
        repaint();
    }
}
