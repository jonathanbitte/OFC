package frc.robot.subsystem;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.LimelightHelpers;

public class Limelight_Sub extends SubsystemBase {

    private static final String LIMELIGHT_NAME = "lime";

@Override
public void periodic() {

    boolean temTag = LimelightHelpers.getTV("lime");
    double id = LimelightHelpers.getFiducialID("lime");
    double tx = LimelightHelpers.getTX("lime");
    double ty = LimelightHelpers.getTY("lime");

    SmartDashboard.putBoolean("Limelight/Tem Tag", temTag);
    SmartDashboard.putNumber("Limelight/ID", id);
    SmartDashboard.putNumber("Limelight/TX", tx);
    SmartDashboard.putNumber("Limelight/TY", ty);
}
    public boolean temTag() {
        return LimelightHelpers.getTV(LIMELIGHT_NAME);
    }

    public double getTagID() {
        return LimelightHelpers.getFiducialID(LIMELIGHT_NAME);
    }
}