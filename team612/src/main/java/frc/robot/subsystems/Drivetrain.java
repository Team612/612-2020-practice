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
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Drivetrain extends SubsystemBase {

private WPI_TalonSRX talon_fr_drive = new WPI_TalonSRX(Constants.talon_fr_port);
private WPI_TalonSRX talon_fl_drive = new WPI_TalonSRX(Constants.talon_fl_port);
private WPI_TalonSRX talon_br_drive = new WPI_TalonSRX(Constants.talon_br_port);
private WPI_TalonSRX talon_bl_drive = new WPI_TalonSRX(Constants.talon_bl_port);

//Ultrasonic 
private Ultrasonic ultra = new Ultrasonic(Constants.ultrasonic_ping_port, Constants.ultrasonic_echo_port);

private DoubleSolenoid solenoid_drive = new DoubleSolenoid(Constants.solenoid_forward_channel, Constants.solenoid_reverse_channel);

public void westCoastDrive(double leftCommand, double rightCommand, double deadzone){  

  //sets up deadzones
  leftCommand = leftCommand < deadzone ? 0.0 : leftCommand;
  rightCommand = rightCommand < deadzone ? 0.0 : rightCommand;

  //right side motor controls
  talon_fr_drive.set(rightCommand);
  talon_br_drive.set(rightCommand);

  //left side motor controls
  talon_fl_drive.set(leftCommand);
  talon_bl_drive.set(leftCommand);

}

public double getDistance() {
  return ultra.getRangeInches();
}

public void shiftForward(){
  solenoid_drive.set(Value.kForward);
  solenoid_drive.set(Value.kOff);
  
}
public void shiftReverse(){
  solenoid_drive.set(Value.kReverse);
  solenoid_drive.set(Value.kOff);
}
  public Drivetrain() {
    ultra.setEnabled(true);
    ultra.setAutomaticMode(true);
 }

  @Override
  public void periodic() {

  }
  
}
