
package frc.robot.command;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.subsystem.Limelight_Sub;
import frc.robot.subsystem.Shooter_Sub;

public class Limelight_Shooter_Test extends Command {

    private final Limelight_Sub limelight;
    private final Shooter_Sub sht;

    public Limelight_Shooter_Test(
        Limelight_Sub limelight,
        Shooter_Sub sht
    ) {
        this.limelight = limelight;
        this.sht = sht;

        addRequirements(sht);
    }

    @Override
    public void initialize() {
        System.out.println("AUTONOMOUS LIMELIGHT INICIADO");
    }

    @Override
    public void execute() {

        boolean temTag = limelight.temTag();
        double id = limelight.getTagID();

        boolean idCorreto = temTag && id == 2.0;

        SmartDashboard.putBoolean(
            "Shooter/ID2 Detectada",
            idCorreto
        );

        if (idCorreto) {
            sht.girar(1.0);
            SmartDashboard.putString(
                "Shooter/Estado",
                "LIGADO - ID 2"
            );
        } else {
            sht.parar();
            SmartDashboard.putString(
                "Shooter/Estado",
                "DESLIGADO"
            );
        }
    }

    @Override
    public void end(boolean interrupted) {
        sht.parar();
        System.out.println("AUTONOMOUS LIMELIGHT ENCERRADO");
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}