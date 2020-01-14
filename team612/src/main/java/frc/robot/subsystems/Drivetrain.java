/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;

public class Drivetrain extends SubsystemBase {
  WPI_TalonSRX frTalon = new WPI_TalonSRX(RobotContainer.frWheelPort);
  WPI_TalonSRX flTalon = new WPI_TalonSRX(RobotContainer.flWheelPort);
  WPI_TalonSRX brTalon = new WPI_TalonSRX(RobotContainer.brWheelPort);
  WPI_TalonSRX blTalon = new WPI_TalonSRX(RobotContainer.blWheelPort);

  public Drivetrain() {
    frTalon.setNeutralMode(NeutralMode.Brake);
    flTalon.setNeutralMode(NeutralMode.Brake);
    brTalon.setNeutralMode(NeutralMode.Brake);
    blTalon.setNeutralMode(NeutralMode.Brake);
  }

  @Override
  public void periodic() {

  }
  
  public WPI_TalonSRX getTalon(int talon) {
    switch (talon) {
      case 1:
        return frTalon;
      case 2:
        return flTalon;
      case 3:
        return brTalon;
      case 4:
        return blTalon;
      default:
        return null;
    }
  }

  public enum DriveTalon {
    FrontRight(1),
    FrontLeft(2),
    BackRight(3),
    BackLeft(4);

    public final int value;

    private DriveTalon(int value) {
      this.value = value;
    }
  }
}
