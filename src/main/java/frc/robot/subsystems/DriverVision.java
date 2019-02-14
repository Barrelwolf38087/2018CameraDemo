package frc.robot.subsystems;

import frc.robot.VisionThread;
import frc.robot.RobotMap;
import frc.robot.commands.SwitchCamera;

import edu.wpi.first.cameraserver.CameraServer;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Subsystem for handling driver vision
 */
public class DriverVision extends Subsystem {
    
    public static enum Camera {
        FRONT, REAR
    }

    public DriverVision() {
        CameraServer.getInstance().addAxisCamera(RobotMap.frontCameraLabel, RobotMap.frontCameraHostname);
        CameraServer.getInstance().addAxisCamera(RobotMap.rearCameraLabel, RobotMap.rearCameraHostname);
        CameraServer.getInstance().addAxisCamera(RobotMap.turretCameraLabel, RobotMap.turretCameraHostname);

        VisionThread.getInstance().start();
    }

    protected void initDefaultCommand() {
        setDefaultCommand(new SwitchCamera());
    }

    public static synchronized void useCamera(Camera camera) {
        VisionThread.getInstance().useCamera(camera);
    }
}