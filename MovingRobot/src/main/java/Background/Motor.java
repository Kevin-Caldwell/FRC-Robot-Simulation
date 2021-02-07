package Background;

public class Motor {
    public int DeviceNumber;
    double speed = 0;

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
