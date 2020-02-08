/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
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
  public String Speed;
  // Ultrasonic sensor for drive

  //Grayhill Quadrature Encoders| Encoder(DIO Port1, DIO Port2, Inversion Factor, Encoding Type)| default4x
  private Encoder encoder_fr = new Encoder(Constants.encoder_ports[0][0], Constants.encoder_ports[1][0], false, Encoder.EncodingType.k4X);
  private Encoder encoder_fl = new Encoder(Constants.encoder_ports[0][1], Constants.encoder_ports[1][1], false, Encoder.EncodingType.k4X);
  private Encoder encoder_br = new Encoder(Constants.encoder_ports[0][2], Constants.encoder_ports[1][2], false, Encoder.EncodingType.k4X); 
  private Encoder encoder_bl = new Encoder(Constants.encoder_ports[0][3], Constants.encoder_ports[1][3], false, Encoder.EncodingType.k4X);

  private double encoderPulse = 256; // 256 pulses for 1 encoder revolution
  private double calculatedEncoderDistance = 1.0471975512; //distance wheel travels in one revolution- moves 1.047 feet 

  private Ultrasonic ultrasonic_drive = new Ultrasonic(Constants.ultrasonic_ping_port, Constants.ultrasonic_echo_port);

  // Double solenoid for changing gears
  private DoubleSolenoid solenoid_drive_2 = new DoubleSolenoid(0, 1);
  private DoubleSolenoid solenoid_drive_1 = new DoubleSolenoid(2, 3);

  // Arcade drive function (same as tank drive)
  public void arcadeDrive(double x_axis, double y_axis, double deadzone) {  
    //sets up deadzones
    x_axis = Math.abs(x_axis) < deadzone ? 0.0 : x_axis;
    y_axis = Math.abs(y_axis) < deadzone ? 0.0 : y_axis;

    //WPI_Talon SRX Caps voltage at 1.0
    double leftCommand = y_axis + x_axis;
    double rightCommand = y_axis - x_axis;
    
    // right side motor controls
    talon_br_drive.set(-rightCommand);
    talon_br_drive.set(-rightCommand);

    //left side motor controls
    talon_fl_drive.set(leftCommand);
    talon_bl_drive.set(leftCommand);
  }

  // Get distance in inches from ultrasonic in drive
  public double getDistance() {
    return ultrasonic_drive.getRangeInches();
  }

  // Shift the double solenoid to kForward
  public void shiftForward(){
    solenoid_drive_1.set(Value.kForward);
    //solenoid_drive_1.set(Value.kOff);

    solenoid_drive_2.set(Value.kForward);
    //solenoid_drive_2.set(Value.kOff);
    System.out.println("forward");

  }

  // Shift the double solenoid to kReverse
  public void shiftReverse(){
    solenoid_drive_1.set(Value.kReverse);
    //solenoid_drive_1.set(Value.kOff);

    solenoid_drive_2.set(Value.kReverse);
    //solenoid_drive_2.set(Value.kOff);
    System.out.println("reversed");
  }

  public void resetEncoders(){
     encoder_bl.reset();
     encoder_br.reset();
     encoder_fl.reset();
     encoder_fr.reset();
  }
// setDistancePerPulse(Distance/PulsePerRevolution)
  public void calibrateEncoderDistance(){
    encoder_bl.setDistancePerPulse(calculatedEncoderDistance/encoderPulse);
    encoder_br.setDistancePerPulse(calculatedEncoderDistance/encoderPulse);
    encoder_fl.setDistancePerPulse(calculatedEncoderDistance/encoderPulse);
    encoder_fr.setDistancePerPulse(calculatedEncoderDistance/encoderPulse);
  }

  public void setEncoderSampleRate(int samplerate){
     encoder_bl.setSamplesToAverage(samplerate);
     encoder_br.setSamplesToAverage(samplerate);
     encoder_fl.setSamplesToAverage(samplerate);
     encoder_fr.setSamplesToAverage(samplerate);
  }
  

  public Drivetrain() {
    // Prepare and enable ultrasonic
    ultrasonic_drive.setEnabled(true);
    ultrasonic_drive.setAutomaticMode(true);
  }
  

  // Periodic loop for ShuffleBoard values
  @Override
  public void periodic() {
    // Shuffle board values
    SmartDashboard.putNumber("Back Left Drive Talon", talon_bl_drive.get());
    SmartDashboard.putNumber("Back Right Drive Talon", talon_br_drive.get());
    SmartDashboard.putNumber("Front Left Drive Talon", talon_fl_drive.get());
    SmartDashboard.putNumber("Front RIght Drive Talon", talon_fr_drive.get());
    SmartDashboard.putNumber("Ultrasonic Distance", getDistance());
    System.out.println(encoder_bl.getDistance());
    System.out.println(encoder_br.getDistance());
    System.out.println(encoder_fl.getDistance());
    System.out.println(encoder_fr.getDistance());

    System.out.println(encoder_bl.getRate());
    System.out.println(encoder_br.getRate());
    System.out.println(encoder_fl.getRate());
    System.out.println(encoder_fr.getRate());
  }
  
}
