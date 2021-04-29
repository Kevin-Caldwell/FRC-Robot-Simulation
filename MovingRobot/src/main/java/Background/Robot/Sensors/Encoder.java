package Background.Robot.Sensors;

public class Encoder {

    public double distance;

    public Encoder(){

    }

    public void updateDistance(double updateDistance){
        distance += updateDistance;
    }

    public void resetDistance(){
        distance = 0;
    }


}
