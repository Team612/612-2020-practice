/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Drivetrain extends SubsystemBase {

  // Talons for drivetrain
  private WPI_TalonSRX talon_fr_drive = new WPI_TalonSRX(Constants.talon_fr_port);
  private WPI_TalonSRX talon_fl_drive = new WPI_TalonSRX(Constants.talon_fl_port);
  private WPI_TalonSRX talon_br_drive = new WPI_TalonSRX(Constants.talon_br_port);
  private WPI_TalonSRX talon_bl_drive = new WPI_TalonSRX(Constants.talon_bl_port);

  // West coast drive function (same as tank drive)
  public void tankDrive(double left_command, double right_command) {
    talon_fr_drive.set(right_command);
    talon_br_drive.set(right_command);
    talon_fl_drive.set(-left_command);
    talon_bl_drive.set(-left_command);
  }

  // Periodic loop for ShuffleBoard values
  @Override
  public void periodic() {
    // Shuffle board values
    SmartDashboard.putNumber("Back Left Drive Talon", talon_bl_drive.get());
    SmartDashboard.putNumber("Back Right Drive Talon", talon_br_drive.get());
    SmartDashboard.putNumber("Front Left Drive Talon", talon_fl_drive.get());
    SmartDashboard.putNumber("Front RIght Drive Talon", talon_fr_drive.get());
  }
  
}