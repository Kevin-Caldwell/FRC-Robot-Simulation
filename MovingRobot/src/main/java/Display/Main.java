package Display;

import GameConstraints.Timer;

/**
 * Hello world!
 */
public final class Main {

    public static RunRobot bot;

    public static void main(String[] args) {
        System.out.println("Simulation Starting...");
        new RunRobot();
        Timer t = new Timer();
        t.start();
        System.out.println("Simulation has Stopped...");
    }
}
