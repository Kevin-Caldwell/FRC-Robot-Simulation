package Background.Robot.Sensors;

import Background.Robot.Robot;

public class Gyro {

    public Gyro() {

    }

    public double getAngle() {
        return Robot.theta;
    }
}
