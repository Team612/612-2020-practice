/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DoubleSolenoid;

import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


public class Drivetrain extends SubsystemBase {
  WPI_TalonSRX frTalon = new WPI_TalonSRX(Constants.frTalonPort);
  WPI_TalonSRX flTalon = new WPI_TalonSRX(Constants.flTalonPort);
  WPI_TalonSRX brTalon = new WPI_TalonSRX(Constants.brTalonPort);
  WPI_TalonSRX blTalon = new WPI_TalonSRX(Constants.blTalonPort);

  Ultrasonic ultrasonicDrive = new Ultrasonic(Constants.ultrasonicPingPort, Constants.ultrasonicEchoPort);
  DoubleSolenoid solenoidDrive = new DoubleSolenoid(Constants.solenoidForwardChannel, Constants.solenoidReverseChannel);
  AHRS navX = new AHRS(Constants.navXPort);

  private boolean drivePIDEnabled = false;

  
  public void westCoastDrive(double leftCommand, double rightCommand, double deadzone) {
    leftCommand = Math.abs(leftCommand) < deadzone ? 0.0 : leftCommand;
    rightCommand = Math.abs(rightCommand) < deadzone ? 0.0 : rightCommand;

    frTalon.set(rightCommand);
    brTalon.set(rightCommand);
    
    flTalon.set(leftCommand);
    frTalon.set(leftCommand);
  }

  public double getDistance() {
    return ultrasonicDrive.getRangeInches();
  }
  
  public void shiftForward() {
    solenoidDrive.set(Value.kForward);
    solenoidDrive.set(Value.kOff);
  }

  public void shiftReverse() {
    solenoidDrive.set(Value.kReverse);
    solenoidDrive.set(Value.kOff);
  }
//Enable/Disable drivePID assistance 
  public void drivePID(){
    drivePIDEnabled = !drivePIDEnabled;
  }
  
  public Drivetrain() {
    ultrasonicDrive.setEnabled(true);
    ultrasonicDrive.setAutomaticMode(true);
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Back Left Drive Talon", blTalon.get());
    SmartDashboard.putNumber("Back Right Drive Talon", brTalon.get());
    SmartDashboard.putNumber("Front Left Drive Talon", flTalon.get());
    SmartDashboard.putNumber("Front Right Drive Talon", frTalon.get());
    SmartDashboard.putNumber("Ultrasonic Distance", getDistance());
    
   
  }
}
