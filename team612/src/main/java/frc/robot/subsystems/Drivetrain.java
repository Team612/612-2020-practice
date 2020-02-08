/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Drivetrain extends SubsystemBase {

  public static final double deadzone = 0.1;

  //initializing arcade drive train talons
  //private static Spark talon_fr = new Spark(Constants.talon_fr_port);
  private static Spark talon_fl = new Spark(Constants.talon_fl_port);
  //private static Spark talon_br = new Spark(Constants.talon_br_port);
  private static Spark talon_bl = new Spark(Constants.talon_bl_port);

  public static void arcadeInput(double x_axis , double y_axis){

    /*
    //deadzone conditions
    x_axis = Math.abs(x_axis) < deadzone ? 0.0 : x_axis;
    y_axis = Math.abs(y_axis) < deadzone ? 0.0 : y_axis;
    
    //Made drive logic
    double leftCommand = y_axis - x_axis;
    double rightCommand = y_axis + x_axis;
    */
    //setting talons to correct direction
    //talon_fr.set(y_axis);
    //talon_br.set(y_axis);
    talon_fl.set(y_axis);
    talon_bl.set(y_axis);
  }

  public Drivetrain() {
    //inverting axes
    //talon_fr.setInverted(true);
    //talon_br.setInverted(true);

  }

  @Override
  public void periodic() {
    /*
    SmartDashboard.putNumber();
    SmartDashboard.putNumber("Talon BR Current", talon_br.getOutputCurrent());
    SmartDashboard.putNumber("Talon FR Current", talon_fr.getOutputCurrent());
    SmartDashboard.putNumber("Talon FL Current", talon_fl.getOutputCurrent());
    */
    
  }
  
}
