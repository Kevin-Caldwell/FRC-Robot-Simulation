package Background.Graphics;

//import Constraints.Field;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import Background.RunRobot;
import Background.Game.AddObstacle;
import Background.Game.Game;
import Background.Input.Joystick;
import Background.Input.Listeners.KeyListener.KeyControls;
import Background.Input.Listeners.MouseListeners.MouseTracker;
import Background.Input.Listeners.MouseListeners.PointerTracker1;
import Background.Input.Listeners.MouseListeners.PointerTracker2;
import Background.Robot.Robot;

/**
 * Main JFrame used for displaying Canvas and Buttons.
 * @author Kevin Caldwell
 */
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
    public JButton mode;

    public Display() {
        System.out.println("Starting Display...");

        b = new RobotPaint();
        b.setBounds(0, 0, 500, 500);
        b.addKeyListener(new KeyControls());

        kc = new KeyControls();
        b.addKeyListener(kc);
        b.addMouseListener(new MouseTracker());

        j1 = new JoystickPanel(axis1);
        j1.setBounds(510, 0, 100, 100);
        j1.addMouseListener(new PointerTracker1());

        j2 = new JoystickPanel(axis2);
        j2.setBounds(620, 0, 100, 100);
        j2.addMouseListener(new PointerTracker2());

        addObstacle = new JButton("Add obstacles");
        addObstacle.setBounds(510, 120, 150, 50);
        addObstacle.addActionListener(new AddObstacle());

        JButton resetButton = new JButton("Reset Position");
        resetButton.setBounds(510, 210, 150, 50);
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
                if(!RunRobot.currCommand.isRunning){
                    MainCode.Robot.dt.initDefaultCommand();
                }
            }
        });

        JButton startGame = new JButton("Start Game");
        startGame.setBounds(510, 280, 150, 50);
        startGame.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new Game();
            }
        });

        mode = new JButton("Drive Mode");
        mode.setBounds(510, 330, 150, 50);

        add(j1);
        add(j2);
        add(b);
        add(addObstacle);
        add(resetButton);
        add(startGame);
        add(mode);

        setSize(new Dimension(750, 750));
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        System.out.println("Canvas has focus: " + b.requestFocusInWindow());
        
    }
}
