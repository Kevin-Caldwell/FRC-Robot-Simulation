package Display;

public class Joystick {
    private double x = 0;
    private double y = 0;

    public Joystick() {

    }

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
