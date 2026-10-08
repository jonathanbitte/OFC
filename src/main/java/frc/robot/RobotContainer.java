package frc.robot;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.auto.NamedCommands;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.command.*;
import frc.robot.subsystem.*;

public class RobotContainer {

    private final Joystick driverjoy = new Joystick(0);
    private final Joystick opjoy = new Joystick(1);

    private final Drive_Sub drive = new Drive_Sub();
    private final Shooter_Sub sht = new Shooter_Sub();
    private final Limelight_Sub limelight = new Limelight_Sub();

    public RobotContainer() {

        drive.setDefaultCommand(
            new Drive_Command(
                drive,

                // Frente / trás
                () -> MathUtil.applyDeadband(
                    driverjoy.getRawAxis(1),
                    0.05
                ),

                // Rotação
                () -> MathUtil.applyDeadband(
                    driverjoy.getRawAxis(2),
                    0.05
                )
            )
        );

        new JoystickButton(driverjoy, 7)
    .whileTrue(new Shooter_Command(sht));
    NamedCommands.registerCommand("Lime",new Limelight_Shooter_Test(limelight, sht));
    }

public Command getAutonomousCommand() {
    return AutoBuilder.buildAuto("Lime");
}
}

