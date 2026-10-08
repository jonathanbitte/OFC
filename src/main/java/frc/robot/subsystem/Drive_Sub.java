package frc.robot.subsystem;
import com.ctre.phoenix6.hardware.Pigeon2;
import com.revrobotics.spark.SparkBase;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drive_Sub extends SubsystemBase {
    //Motores
    private final SparkMax LeftMaster = new SparkMax(1, MotorType.kBrushed);
    private final SparkMax RightMaster = new SparkMax(2, MotorType.kBrushed);
    private final SparkMax LeftFollow = new SparkMax(3, MotorType.kBrushed);
    private final SparkMax RightFollow = new SparkMax(4, MotorType.kBrushed);

    //Pigeon
    private final Pigeon2 gyro = new Pigeon2(1);
    
    //Diferential drive
    DifferentialDrive drive = new DifferentialDrive(LeftMaster, RightMaster);

    //PID
    PIDController PID = new PIDController(1.5, 0, 0);

    public Drive_Sub() {
        //Cria as configurações dos motores
        SparkMaxConfig masterConfig_1 = new SparkMaxConfig();
        SparkMaxConfig masterConfig_2 = new SparkMaxConfig();
        SparkMaxConfig rigthfollowConfig = new SparkMaxConfig();
        SparkMaxConfig leftfollowConfig = new SparkMaxConfig();

        //inverte os motores diretos
        masterConfig_1.inverted(false);
        masterConfig_2.inverted(true);

        //Define os motores followers como followers
        rigthfollowConfig.follow(RightMaster.getDeviceId());
        leftfollowConfig.follow(LeftMaster.getDeviceId());

        drive.setSafetyEnabled(false);

        //Define a configuração de cada motor
        LeftMaster.configure(masterConfig_1, SparkBase.ResetMode.kResetSafeParameters, SparkBase.PersistMode.kNoPersistParameters);
        RightMaster.configure(masterConfig_2, SparkBase.ResetMode.kResetSafeParameters, SparkBase.PersistMode.kNoPersistParameters);
        LeftFollow.configure(leftfollowConfig, SparkBase.ResetMode.kResetSafeParameters, SparkBase.PersistMode.kNoPersistParameters);
        RightFollow.configure(rigthfollowConfig, SparkBase.ResetMode.kResetSafeParameters, SparkBase.PersistMode.kNoPersistParameters);

        //Sim PID
        SmartDashboard.putNumber("PID/P", PID.getP());
        SmartDashboard.putNumber("PID/I", PID.getI());
        SmartDashboard.putNumber("PID/D", PID.getD());
    }
    
    @Override
    public void periodic() {
        double P = SmartDashboard.getNumber("PID/P", 1.5);
        double I = SmartDashboard.getNumber("PID/I", 0);
        double D = SmartDashboard.getNumber("PID/D", 0);

        SmartDashboard.putNumber("motor/ESQ", LeftMaster.get());
        SmartDashboard.putNumber("motor/DIR", RightMaster.get());

        PID.setPID(P, I, D);
    }
    
    public double getHeading(){
        return gyro.getYaw().getValueAsDouble();
    }

    public void arcadeDrive(double forward, double rotation) {
        double correction = 0;
        if (Math.abs(rotation) < 0.05) {
            correction = PID.calculate(getHeading(),0);
        }
        drive.arcadeDrive(forward, rotation - correction);
    }
}