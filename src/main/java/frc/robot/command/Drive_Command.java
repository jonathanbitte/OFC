package frc.robot.command;
import edu.wpi.first.wpilibj2.command.Command;
import java.util.function.DoubleSupplier;
import frc.robot.subsystem.Drive_Sub;

public class Drive_Command extends Command {
    private final Drive_Sub driveSubsystem;
    private final DoubleSupplier forwardSupplier;
    private final DoubleSupplier rotationSupplier;

    public Drive_Command(Drive_Sub driveSubsystem, DoubleSupplier forwardSupplier, DoubleSupplier rotationSupplier) {
        this.driveSubsystem = driveSubsystem;
        this.forwardSupplier = forwardSupplier;
        this. rotationSupplier = rotationSupplier;

        addRequirements(driveSubsystem);
    }

    @Override
    public void execute(){
        double forward = forwardSupplier.getAsDouble();
        double rotation = rotationSupplier.getAsDouble();
        driveSubsystem.arcadeDrive(forward*0.75, rotation*0.75);
    }

    @Override
    public void end(boolean interrupted) {
        driveSubsystem.arcadeDrive(0, 0);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
