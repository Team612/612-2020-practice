/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Timer;

import edu.wpi.first.wpilibj2.command.CommandBase;
import com.analog.adis16448.frc.ADIS16448_IMU;
import com.kauailabs.navx.frc.AHRS;

public class VelocityNavx extends CommandBase {
  
  private double initialTimer = 0;
  private double currentTimer;

  private double currentAccelX;
  private double currentAccelY;
  private double currentAccelZ;
  //private double[] currentAccelXYZ = {0, 0, 0}; //might want to use arrays
  private double initialAccelX; 
  private double initialAccelY; 
  private double initialAccelZ; 

  private double currentVelocityX;
  private double currentVelocityY;
  private double currentVelocityZ; 

  private double initialVelocityX = 0;
  private double initialVelocityY = 0;
  private double initialVelocityZ = 0; 

  private double displacementX = 0; 
  private double displacementY = 0; 
  private double displacementZ = 0; 

  Timer timer = new Timer(); 

  AHRS navx = new AHRS(SPI.Port.kMXP);

  public VelocityNavx() {
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    initialAccelX = 0;
    initialAccelY = 0;
    initialAccelZ = 0;
    //currentAccelXYZ[0] = 0;
    //currentAccelXYZ[1] = 0;
    //currentAccelXYZ[2] = 0;
    timer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    timer.reset(); // reset timer for next iteration of summation. Sets time = 0.
 
    //System.out.println(accel.getAccelInstantX());
    //System.out.println(accel.getAccelInstantY());
    //System.out.println(accel.getAccelInstantZ());

    getAccelerometer(); // Capture 3-Axis accelerometer values.
    getTimer();  // Capture timer value
    

    trapezoidalReimannX(); // Trapezoidal Reimann sum for X-Axis displacement
    trapezoidalReimannY(); // Trapezoidal Reimann sum for Y-Axis displacement
    trapezoidalReimannZ(); // Trapezoidal Reimann sum for Z-Axis displacement

    updateValues(); // Update calculation values for next iteration of summation. Sets initial value to current value. 
    

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(final boolean interrupted) {
    timer.stop(); 
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
/*
  private void trapezoidalReimann(double vel, double ia, double ca, double ct, double it) { // 
    vel+""

    vel = this.initial + ((ia + ca)/2.0)*(ct - it);
  }*/

  private void getAccelerometer(){
    currentAccelX = navx.getRawAccelX();
    currentAccelY = navx.getRawAccelY();
    currentAccelZ = navx.getRawAccelZ();
  }

  private void getTimer(){
    currentTimer = timer.get();
  }

  private void trapezoidalReimannX(){
      currentVelocityX = initialVelocityX + ((initialAccelX + currentAccelX)/2.0)*(currentTimer - initialTimer);

      displacementX = displacementX + ((initialVelocityX + currentVelocityX)/2.0)*(currentTimer - initialTimer);
  }

  private void trapezoidalReimannY(){
      currentVelocityY = initialVelocityY + ((initialAccelY + currentAccelY)/2.0)*(currentTimer - initialTimer);
      displacementY = displacementY + ((initialVelocityY + currentVelocityY)/2.0)*(currentTimer - initialTimer);

  }

  private void trapezoidalReimannZ(){
      currentVelocityZ = initialVelocityZ + ((initialAccelZ + currentAccelZ)/2.0)*(currentTimer - initialTimer);
      displacementZ = displacementZ + ((initialVelocityZ + currentVelocityZ)/2.0)*(currentTimer - initialTimer);

  }

  private void midpointReimannX(){
    //figure out a way to find accelorometer value at midpoint of time readings
    //1, store all accelerometer values and then do midpoints from that
    //2, gradually create regression function that can approximate midpoint, big brain
    //4, or just average right left and trapezoidal
    //midpoint reimann averaged with trapezoidal will give us the best result
  }

  private void midpointReimannY(){

  }

  private void midpointReimannZ(){

  }

  private void updateValues(){
    initialAccelZ = currentAccelZ; 
    initialAccelY = currentAccelY; 
    initialAccelX = currentAccelX; 
    initialVelocityX = currentVelocityX;
    initialVelocityY = currentVelocityY;
    initialVelocityZ = currentVelocityZ;
    initialTimer  = currentTimer; 
  }
}
