package Background;

/**
 * Motor class.
 */
public class Motor {
    public int DeviceNumber;
    double speed = 0;

    /**
     * Creates a new Motor. Acceptable Device numbers are only 1 and -1. 1 is a
     * right side motor and -1 is a left side motor.
     */
    public Motor(int DeviceNumber) {
        this.DeviceNumber = DeviceNumber;
    }

    public void set(double speed) {
        this.speed = speed;
    }

    public double getSpeed() {
        return speed;
    }
}
