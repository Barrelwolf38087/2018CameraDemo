package frc.robot.commands;

import frc.robot.DTI;
import frc.robot.Robot;
import frc.robot.subsystems.DriverVision;

import edu.wpi.first.wpilibj.command.Command;

public class SwitchCamera extends Command {

    public SwitchCamera() {
        requires(Robot.driverVision);
    }

    protected void execute() {

        if (DTI.operatorStick.getRawButton(DTI.NykoController.rightTrigger))
            DriverVision.useCamera(DriverVision.Camera.REAR);
        else
            DriverVision.useCamera(DriverVision.Camera.FRONT);
    }

    protected boolean isFinished() {
        return false;
    }
}