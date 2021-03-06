package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
//import edu.wpi.first.wpilibj.command.Command;
// import org.usfirst.frc.team138.robot.commands.*;
import frc.robot.commands.BaseCommand;
import frc.robot.commands.TeleopDrive;
import frc.robot.commands.Vibrate;
import frc.robot.commands.Wait;
// import org.usfirst.frc.team138.robot.commands.ControlWinch;

// import org.usfirst.frc.team138.robot.subsystems.Elevator.ElevatorTarget;

/**
 * Deprecated. Use {@link frc.robot.DTI DTI} instead.
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 * @see frc.robot.DTI
 */
@Deprecated
public final class OI {
	
	// Xbox Controller Map
	static final int xboxController = 0;
	// Xbox Buttons
	static final int xboxA = 1;
	static final int xboxB = 2;
	static final int xboxX = 3;
	static final int xboxY = 4;
	static final int xboxLeftBumper = 5;
	static final int xboxRightBumper = 6;
	static final int xboxLeftStick = 7;
	static final int xboxRightStick = 8;
	static final int xboxMenu = 9;
	static final int xboxView = 10;
	static final int xboxHome = 11;
	static final int xboxDpadUp = 12;
	static final int xboxDpadDown = 13;
	static final int xboxDpadLeft = 14;
	static final int xboxDpadRigt = 15;
	
	//Xbox axes
	static final int xboxLeftXAxis = 0;
	static final int xboxLeftYAxis = 1;
	static final int xboxLeftTriggerAxis = 2;
	static final int xboxRightTriggerAxis = 3;
	static final int xboxRightXAxis = 4;
	static final int xboxRightYAxis = 5;
	
	// Nyko Air Flow Controller Map
	static final int nykoController = 1;
	// Nyko buttons
	static final int nykoButton1 = 1;
	static final int nykoButton2 = 2;
	static final int nykoButton3 = 3;
	static final int nykoButton4 = 4;
	static final int nykoLeftBumper = 5;
	static final int nykoRightBumper = 6;
	static final int nykoLeftTrigger = 7;
	static final int nykoRightTrigger = 8;
	static final int nykoMiddle9 = 9;
	static final int nykoMiddle10 = 10;
	static final int nykoMiddle11 = 11;
	static final int nykoLeftStick = 12;
	static final int nykoRightStick = 13;
	
	// Nyko axes
	static final int nykoLeftXAxis = 0;		// X Axis on Driver Station
	static final int nykoLeftYAxis = 1;		// Y Axis on Driver Station
	static final int nykoRightYAxis = 2;	// Z Axis on Driver Station
	static final int nykoRightXAxis = 3;	// Rotate Axis on Driver Station
	
    static Joystick driverStick = new Joystick(xboxController);
    static Joystick operatorStick = new Joystick(nykoController);
    
    // Driver Stick
    static Button winchButton 		= new JoystickButton(driverStick, xboxA);
	static Button xboxMenuButton = new JoystickButton(driverStick,xboxMenu);
    
    // Operator Stick
    static Button elevateToAcquireButton = new JoystickButton(operatorStick, nykoButton1);
    static Button elevateToSwitchButton = new JoystickButton(operatorStick, nykoButton2);
    static Button elevateToScaleButton = new JoystickButton(operatorStick, nykoButton4);
    static Button acquireButton = new JoystickButton(operatorStick, nykoLeftTrigger);
    static Button releaseButton = new JoystickButton(operatorStick, nykoRightTrigger);
    static Button readyToAcquireButton = new JoystickButton(operatorStick, nykoLeftBumper);
    static Button toggleWristButton = new JoystickButton(operatorStick, nykoRightBumper);
    static Button homeElevatorButton = new JoystickButton(operatorStick, nykoMiddle11);
    static Button cancelElevatorMoveButton = new JoystickButton(operatorStick, nykoRightStick);
    static Button alternateElevatorTargetButton = new JoystickButton(operatorStick, nykoButton3);
    static Button toggleGrasperButton = new JoystickButton(operatorStick, nykoMiddle9);
    static Button toggleRollersButton = new JoystickButton(operatorStick, nykoMiddle10);
	static Button toggleToClimb = new JoystickButton(operatorStick,nykoLeftStick);
//    static Button simulateDetectCubeButton = new JoystickButton(operatorStick, nykoMiddle9);
//    static Button simulateAcquireCubeButton = new JoystickButton(operatorStick, nykoMiddle10);
    
    static double lastX=0;
    static double LastY=0;
    
    public OI(){
    	//Driver Stick
		// winchButton.whileHeld(new ControlWinch());
		xboxMenuButton.whileHeld(new Vibrate(driverStick));
    	
    	//Operator Stick
    	// elevateToAcquireButton.whenPressed(new ElevateToTarget(ElevatorTarget.ACQUIRE));
    	// elevateToSwitchButton.whenPressed(new ElevateToTarget(ElevatorTarget.SWITCH));
    	// elevateToScaleButton.whenPressed(new ElevateToTarget(ElevatorTarget.LOWER_SCALE));
    	// alternateElevatorTargetButton.whenPressed(new ElevateToAlternateTarget());
    	// cancelElevatorMoveButton.whenPressed(new CancelElevatorMove());
		// homeElevatorButton.whileHeld(new HomeElevator());
		

    	// readyToAcquireButton.whenPressed(new ReadyToAcquire());
    	
    	// acquireButton.whenPressed(new StartAcquire());
    	// acquireButton.whenReleased(new CompleteAcquire());
    	// releaseButton.whenPressed(new StartRelease());
    	// releaseButton.whenReleased(new CompleteRelease());
    	
    	// toggleWristButton.whenPressed(new ToggleWrist());
    	// toggleGrasperButton.whenPressed(new ToggleGrasper());
    	// toggleRollersButton.whenPressed(new ToggleRollers());
    	// toggleToClimb.whenPressed(new PrepareToClimb());
    	
    	
    	//simulateDetectCubeButton.whenPressed(new SimulateDetectCube());
    	//simulateAcquireCubeButton.whenPressed(new SimulateAcquireCube());
    }
    
    
	public static double getMoveSpeed()
	{
		// joystick values are opposite to robot directions
		double moveSpeed=-driverStick.getRawAxis(xboxLeftYAxis);
		// Apply thresholds to joystick positions to eliminate
		// creep motion due to non-zero joystick value when joysticks are 
		// "centered"
		if (Math.abs(moveSpeed) < Constants.CloseLoopJoystickDeadband)
			moveSpeed=0;
		return moveSpeed;
	}
	
	public static double getRotateSpeed()
	{
		double rotateSpeed=-driverStick.getRawAxis(xboxRightXAxis);
		if (Math.abs(rotateSpeed) < Constants.CloseLoopJoystickDeadband)
			rotateSpeed=0;
		return rotateSpeed;
	}
	
	public static double getClimbSpeed()
	{
		// Joystick up returns negative axis values, so inverted
		return (-1 * operatorStick.getRawAxis(nykoLeftYAxis));
	}
	
	//Gets the winch speed based on Xbox triggers
	public static double getWinchSpeed() {
		double winchSpeed = 0.0;
		
		winchSpeed += driverStick.getRawAxis(xboxRightTriggerAxis);
		winchSpeed -= driverStick.getRawAxis(xboxLeftTriggerAxis);
		
		return winchSpeed;
	}
	
	// Return the jog direction: 1 for up, -1 for down
	public static int getJogDirection()
	{
		// POV hat returns 0 for up
		if (operatorStick.getPOV() == 0)
		{
			return 1;
		}
		// POV hat returns 180 for down
		else if (operatorStick.getPOV() == 180)
		{
			return -1;
		}
		else
		{
			return 0;
		}
	}
	
	public static boolean isReverse() {
		return driverStick.getRawButton(xboxB);
	}
	
	public static boolean isFullSpeed() {
		return driverStick.getRawAxis(xboxRightTriggerAxis) > Constants.highSpeedModeTriggerThreshold;
	}
	
	
	    
} // :D

