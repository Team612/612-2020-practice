/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import java.io.IOException;
import java.nio.file.Path;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryUtil;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Drivetrain extends SubsystemBase {
  private double encoderPulse = 256; // 256 pulses for 1 encoder revolution
  private double calculatedEncoderDistance = 1.0471975512; //distance wheel travels in one revolution- moves 1.047 feet 

  //Voltage Feed Forward Values *TBD*
  public static double ks = 0; 
  public static double kv = 0;
  public static double ka = 0;

  public static double kPDriveVel = 0; // drive Proportion Constant

  public static double drivebaselength = 0; // Wheel to Wheel Length *TBD*


  // Motor Controller Construction
  private static SpeedControllerGroup left = new SpeedControllerGroup(new WPI_TalonSRX(1), new WPI_TalonSRX(2));
  private static SpeedControllerGroup right = new SpeedControllerGroup(new WPI_TalonSRX(3), new WPI_TalonSRX(4));
  private static DifferentialDrive drive = new DifferentialDrive(left, right);

  // Encoder Construction
  private Encoder encoder_l = new Encoder(Constants.encoder_ports[0][0], Constants.encoder_ports[1][0], false, Encoder.EncodingType.k4X);
  private Encoder encoder_r = new Encoder(Constants.encoder_ports[0][1], Constants.encoder_ports[1][1], false, Encoder.EncodingType.k4X);

  //Gyroscope Construction
  private AHRS navx = new AHRS(SPI.Port.kMXP);

  
  public static DifferentialDriveKinematics DriveKinematics = new DifferentialDriveKinematics(drivebaselength); // Drive Kinematics 
  private DifferentialDriveOdometry odometry; //Tank Drive Odometry


  public static SendableChooser<String> m_chooser = new SendableChooser<>();

  
  //Create Drivetrain subsystem
  public Drivetrain() {
    calibrateEncoderDistance();
    resetEncoders();
    odometry = new DifferentialDriveOdometry(getRotation());

    m_chooser.setDefaultOption("5balldeposit", "5balldeposit.wpilib.json");
    m_chooser.addOption("test", "test.wpilib.json");
    SmartDashboard.putData("AutoPicker", m_chooser);

  }

  @Override // Periodically Update Tank Drive Odometry
  public void periodic() {
    odometry.update(getRotation(), encoder_l.getDistance(), encoder_r.getDistance());
    
  }


  // Return Rotation2d Object from Gyroscope
  public Rotation2d getRotation() {
    return Rotation2d.fromDegrees(navx.getAngle());
  }
  // Return pose from Tank Drive Odometry
  public Pose2d getPose() {
    return odometry.getPoseMeters();
  }
  // Return Wheel Speeds as Encoder Rate doubles
  public DifferentialDriveWheelSpeeds getWheelspeeds() {
    return new DifferentialDriveWheelSpeeds(encoder_l.getRate(), encoder_r.getRate());
  }


  //Calibrate Encoders
  public void calibrateEncoderDistance() {
    encoder_l.setDistancePerPulse(calculatedEncoderDistance / encoderPulse);
    encoder_r.setDistancePerPulse(calculatedEncoderDistance / encoderPulse);
  }

  // Zero Encoder Distance
  public void resetEncoders() {
    encoder_l.reset();
    encoder_r.reset();
  }
  // Zero Gyroscope Angle
  public void resetRobotDirection() {
    navx.reset();
  }
  // Reset Tank Drive Odometry
  public void resetOdometry(Pose2d pose) {
    resetEncoders();
    odometry.resetPosition(pose, getRotation());
  }
  // Tank Drive Configuration
  public void tankdrive(double leftCommand, double rightCommand) {
    drive.tankDrive(leftCommand, rightCommand);
  }
  // Arcade Drive Configuration
  public void arcadeDrive(double leftCommand, double rightCommand) {
    drive.arcadeDrive(leftCommand, rightCommand);
  }
  // Tank Drive Voltage Compensation Configuration
  public void tankDriveVolts(double leftVoltage, double rightVoltage) {
    left.setVoltage(leftVoltage);
    right.setVoltage(-rightVoltage);
    drive.feed();
  }
  // Average Encoder Distance
  public double getAverageEncoderDistance(){
    return (encoder_l.getDistance() + encoder_r.getDistance())/2.0;
  }
  //Drive Motor Controller Scaling
  public void maxDrive(double max){
    drive.setMaxOutput(max);
  } 
  // NonLinear Trajectory Follower
  public RamseteController getRamseteController(){
    return new RamseteController();
  }
  // Configure FeedForward
  public SimpleMotorFeedforward getSimpleMotorFeedforward(){
    return new SimpleMotorFeedforward(ks, kv, ka);
  }

  // Assigns Selected PathWeaver Trajectory to Ramsete Command
  public Trajectory getTrajectory(){

    
    //Trajectory json location on Computer; 
    String trajectoryJSON = "PathWeaver/output/"  + m_chooser.getSelected();

    Trajectory trajectory = null;
    try {
      Path trajectoryPath = Filesystem.getDeployDirectory().toPath().resolve(trajectoryJSON);
      trajectory = TrajectoryUtil.fromPathweaverJson(trajectoryPath);
      
    } catch (IOException ex) {
      ex.printStackTrace();
      System.out.println();
    }
    return trajectory;
  }
  // Return TankDrive Kinematics
  public DifferentialDriveKinematics getDifferentialDriveKinematics(){
    return DriveKinematics;
  }
  // Return PID controllers
  public PIDController getPIDController(){
    return new PIDController(kPDriveVel, 0, 0);
  }



  
}
