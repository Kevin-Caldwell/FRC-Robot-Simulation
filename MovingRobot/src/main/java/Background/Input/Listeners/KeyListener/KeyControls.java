package Background.Input.Listeners.KeyListener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import Background.RunRobot;

/**
 * KeyListener, not used.
 * @author Kevin Caldwell
 */
public class KeyControls implements KeyListener {

    public boolean keyPressed;

    public KeyControls() {
        System.out.println("Keyboard Controls Initialized");
        keyPressed = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // System.out.println("Key Typed: " + KeyEvent.getKeyText(e.getKeyCode()));
    }

    @Override
    public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();

            if(key == KeyEvent.VK_W){
                RunRobot.display.axis1.setY(RunRobot.display.axis1.get('y') + 0.2);
            }
            if(key == KeyEvent.VK_S){
                RunRobot.display.axis1.setY(RunRobot.display.axis1.get('y') - 0.2);
            }
            if(key == KeyEvent.VK_E){
                RunRobot.display.axis2.setY(RunRobot.display.axis2.get('y') + 0.2);
            }
            if(key == KeyEvent.VK_D){
                RunRobot.display.axis2.setY(RunRobot.display.axis2.get('y') - 0.2);
            }
        // System.out.println(Main.robot.speed);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // System.out.println("Key Released: " + KeyEvent.getKeyText(e.getKeyCode()));

        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                keyPressed = false;
                System.out.println("no up");
                break;
            case KeyEvent.VK_DOWN:
                keyPressed = false;
                System.out.println("no down");
                break;
            default:
                break;
        }

        // System.out.println(Main.robot.speed);
    }
}
