package Background;

/**
 * Joystick Class for keepig track of Input from the two Joystick axes.
 * @author Kevin Caldwell
 */
public class Joystick {
    private double x = 0; // -1 < x < 1
    private double y = 0; // -1 < y < 1

    public Joystick() {

    }

    /**
     * @param axis 'x' or 'y'
     * @return value of the Joystick Axis
     */
    public double get(char axis) {
        if (axis == 'x') {
            return x;
        } else if (axis == 'y') {
            return y;
        }
        return 0;
    }

    public void setX(double speed) {
        x = speed;
    }

    public void setY(double speed) {
        y = speed;
    }
}
