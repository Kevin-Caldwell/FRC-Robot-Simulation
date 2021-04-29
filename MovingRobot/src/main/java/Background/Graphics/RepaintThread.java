package Background.Graphics;

import Background.RunRobot;

public class RepaintThread extends Thread {

    public RepaintThread() {
        this.setName("Repaint Thread");
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (RunRobot.display != null) {
                RunRobot.display.repaint();
            }
        }
    }
}
