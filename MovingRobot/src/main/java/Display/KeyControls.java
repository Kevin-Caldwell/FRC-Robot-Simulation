package Display;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
        // System.out.println("Key Pressed: " + KeyEvent.getKeyText(e.getKeyCode()));

        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
                // System.out.println("yes up");
                keyPressed = true;
                break;
            case KeyEvent.VK_LEFT:
                // System.out.println("yes down");
                keyPressed = true;
                break;
            default:
                break;
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
