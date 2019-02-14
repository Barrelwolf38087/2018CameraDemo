package frc.robot;

import frc.robot.RobotMap;

import frc.robot.subsystems.DriverVision;

import org.opencv.core.Mat;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;

/**
 * Singleton {@link java.lang.Thread Thread} for handling driver vision
 */
public class VisionThread extends Thread {
        
    // Input stream references go here.
    private CvSink frontCameraInputStream, rearCameraInputStream, turretCameraInputStream;

    // Use the front camera by default
    private CvSink unifiedInputStream;
    private DriverVision.Camera currentCamera;

    // Output stream goes here.
    private static CvSource unifiedOutputStream;
    private static CvSource turretOutputStream;

    // Temporary space for frames
    private static Mat unifiedFrameBuffer = new Mat();
    private static Mat turretFrameBuffer  = new Mat();

    // Initialization code goes here
    public VisionThread() {
        frontCameraInputStream  = CameraServer.getInstance().getVideo(RobotMap.frontCameraLabel);
        rearCameraInputStream   = CameraServer.getInstance().getVideo(RobotMap.rearCameraLabel);
        turretCameraInputStream = CameraServer.getInstance().getVideo(RobotMap.turretCameraLabel);
        
        currentCamera = DriverVision.Camera.FRONT;
        unifiedInputStream  = frontCameraInputStream;
        unifiedOutputStream = CameraServer.getInstance().putVideo("Unified Camera", 320, 240);
        turretOutputStream = CameraServer.getInstance().putVideo("Turret Camera", 320, 240);
    }

    // The singleton mechanism
    private static VisionThread thread;
    public static VisionThread getInstance() {
        if (thread == null) {
            thread = new VisionThread();
        }
        return thread;
    }

    // Reassign the input stream
    public synchronized void useCamera(DriverVision.Camera camera) {
        if (currentCamera == camera)
            return;
        
        switch (camera) {
            case FRONT:
                currentCamera = camera;
                unifiedInputStream = frontCameraInputStream;
                break;
            case REAR:
                currentCamera = camera;
                unifiedInputStream = rearCameraInputStream;
                break;
        }
    }

    @Override
    public void run() {

        while (true) {

            // Shove frames into the buffers
            unifiedInputStream.grabFrame(unifiedFrameBuffer);
            turretCameraInputStream.grabFrame(turretFrameBuffer);

            // Make sure the frames aren't empty because everyone will die if they are
            if (!unifiedFrameBuffer.empty())
                unifiedOutputStream.putFrame(unifiedFrameBuffer);

            if (!turretFrameBuffer.empty())
                turretOutputStream.putFrame(turretFrameBuffer);
        }
    }
}