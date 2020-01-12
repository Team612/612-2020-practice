/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Drivetrain extends SubsystemBase {

  // Ultrasonic object creation on ping and echo channel
  public Ultrasonic ultrasonic_drive = new Ultrasonic(Constants.pingChannel, Constants.echoChannel);

  // Temporary talonSRX (will be replaced for west coast)
  private WPI_TalonSRX fr_wheel = new WPI_TalonSRX(3);
  private WPI_TalonSRX bl_wheel = new WPI_TalonSRX(7);
  private WPI_TalonSRX br_wheel = new WPI_TalonSRX(2);
  private WPI_TalonSRX fl_wheel = new WPI_TalonSRX(6);

  public Drivetrain() {
    // Enable the ultrasonic and set automatic mode
    ultrasonic_drive.setEnabled(true);
    ultrasonic_drive.setAutomaticMode(true);
  }

  @Override
  public void periodic() {

  }
  
  // Hacked together tank drive script (will replace)
  public void tank_drive(double left_command, double right_command) {
    fr_wheel.set(right_command);
    br_wheel.set(right_command);
    fl_wheel.set(-left_command);
    bl_wheel.set(-left_command);
  }

  
}
