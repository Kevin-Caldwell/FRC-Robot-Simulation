package GameConstraints;

public class Timer {
    long startTime;
    long elapsedTime;

    public void start() {
        startTime = System.currentTimeMillis();
    }

    public long getTimeElapsed() {
        return System.currentTimeMillis() - startTime;
    }
}
