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

  public static WPI_TalonSRX talon_fl = new WPI_TalonSRX(Constants.talon_fl_port);
  public static WPI_TalonSRX talon_bl = new WPI_TalonSRX(Constants.talon_bl_port);
  public static WPI_TalonSRX talon_fr = new WPI_TalonSRX(Constants.talon_fr_port);
  public static WPI_TalonSRX talon_br = new WPI_TalonSRX(Constants.talon_br_port);

  private static double DEADZONE = .001;
  
  public Drivetrain() {

  }

  public static void tank_drive(double left, double right){
    double leftCommand = deadzone(left), rightCommand = deadzone(right);
    talon_fl.set(leftCommand);
    talon_fr.set(rightCommand);
    talon_bl.set(leftCommand);
    talon_br.set(rightCommand);
    
  }

  private static double deadzone(double input){
    return Math.abs(input) < DEADZONE ? 0 : input; // ternary statement
  }

  @Override
  public void periodic() {

  }
  
}
