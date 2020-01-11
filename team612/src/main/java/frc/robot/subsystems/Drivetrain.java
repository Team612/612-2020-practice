/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Drivetrain extends SubsystemBase {

private WPI_TalonSRX talon_fr_drive = new WPI_TalonSRX(Constants.talon_fr_port);
private WPI_TalonSRX talon_fl_drive = new WPI_TalonSRX(Constants.talon_fl_port);
private WPI_TalonSRX talon_br_drive = new WPI_TalonSRX(Constants.talon_br_port);
private WPI_TalonSRX talon_bl_drive = new WPI_TalonSRX(Constants.talon_bl_port);

public void westCoastDrive(double leftCommand, double rightCommand, double deadzone){  
  
  leftCommand = leftCommand < deadzone ? 0.0 : leftCommand;
  //west coast drive logic

  //right side motor controls
  talon_fr_drive.set(rightCommand);
  talon_br_drive.set(rightCommand);

  //left side motor controls
  talon_fl_drive.set(leftCommand);
  talon_bl_drive.set(leftCommand);


}

  public Drivetrain() {
    
  }

  @Override
  public void periodic() {

  }
  
}
