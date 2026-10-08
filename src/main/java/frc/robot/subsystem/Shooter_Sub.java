package frc.robot.subsystem;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.spark.SparkBase;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

public class Shooter_Sub extends SubsystemBase {

    private final SparkMax sht = new SparkMax(5, MotorType.kBrushless);

    public Shooter_Sub() {

        SparkMaxConfig sht_config = new SparkMaxConfig();

        sht_config
            .inverted(true)
            .idleMode(IdleMode.kCoast)
            .smartCurrentLimit(40);

        sht.configure(
            sht_config,
            SparkBase.ResetMode.kResetSafeParameters,
            SparkBase.PersistMode.kPersistParameters
        );
    }

    public void girar(double velocidade) {
        sht.set(velocidade);
    }

    public void parar() {
        sht.stopMotor();
    }

    public double getVelocidade() {
        return sht.get();
    }
}