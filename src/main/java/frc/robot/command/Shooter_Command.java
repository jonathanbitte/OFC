package frc.robot.command;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystem.Shooter_Sub;

public class Shooter_Command extends Command {

    private final Shooter_Sub shooter;

    public Shooter_Command(Shooter_Sub shooter) {
        this.shooter = shooter;

        addRequirements(shooter);
    }

    @Override
    public void execute() {
        shooter.girar(1);
    }

    @Override
    public void end(boolean interrupted) {
        shooter.parar();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}