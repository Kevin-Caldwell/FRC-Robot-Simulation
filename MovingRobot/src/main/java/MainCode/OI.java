package MainCode;

import Display.Joystick;
import Display.RunRobot;

public class OI {

	public Joystick DriverController = new Joystick();
	public Joystick OperatorController = new Joystick();

	public OI() {

	}

	public double left() {
		if (RunRobot.display != null) {
			double leftdrivestick = RunRobot.display.axis2.get('y') / 50;
			if (Math.abs(leftdrivestick) < 0.05)
				return 0.0;
			else
				return leftdrivestick;
		} else {
			return 0;
		}
	}

	public double right() {
		if (RunRobot.display != null) {
			double rightdrivestick = RunRobot.display.axis1.get('y') / 50;
			//System.out.println(rightdrivestick);
			if (Math.abs(rightdrivestick) < 0.05)
				return 0.0;
			else
				return rightdrivestick;
		} else {
			return 0;
		}
	}
}
