//Main note: RobotMap replaced by Constants (and to some extent RobotContainer), OI by ControlMap, Robot not much use
//DON'T touch Main


package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

// had the following line, WPI suggested that I delete it
//import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class PhoenixDrive extends SubsystemBase {
  //set these talons
  public final WPI_TalonSRX talon_fl = new WPI_TalonSRX(Constants.talon_fl_port);
  public final WPI_TalonSRX talon_bl = new WPI_TalonSRX(Constants.talon_bl_port);
  public final WPI_TalonSRX talon_fr = new WPI_TalonSRX(Constants.talon_fr_port);
  public final WPI_TalonSRX talon_br = new WPI_TalonSRX(Constants.talon_br_port);
  //go to constants

  public PhoenixDrive() {
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
