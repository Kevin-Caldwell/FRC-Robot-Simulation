package Background.Game.GameConstraints;

/**
 * Timer class for keepnig track of Game Time
 */
public class Timer {
    long startTime;
    long elapsedTime;

    /**
     * Stores the start time of the Game
     */
    public void start() {
        startTime = System.currentTimeMillis();
    }

    /**
     * Returns the time elapsed since the start of the game
     */
    public long getTimeElapsed() {
        return System.currentTimeMillis() - startTime;
    }
}
