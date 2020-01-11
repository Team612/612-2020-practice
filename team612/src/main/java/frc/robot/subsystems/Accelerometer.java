/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.analog.adis16448.frc.ADIS16448_IMU;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Accelerometer extends SubsystemBase {
  /**
   * Creates a new Accelerometer.
   */

  private ADIS16448_IMU imu = new ADIS16448_IMU();

  double prevTimeX, prevTimeZ;
  double prevAccelX, prevAccelZ;
  double vZ, vX;
  Timer timer = new Timer(); 

  public Accelerometer() {

  }


  public double getVelocityX() {
    double currentTime = timer.get();
    double currentAccel = imu.getAccelX();

    double velocity = (currentAccel-prevAccelX) * (currentTime-prevTimeX);

    prevTimeX = currentTime;
    prevAccelX = currentAccel;

    return velocity;
  }

  public double getVelocityY() {
    double currentTime = timer.get();
    double currentAccel = imu.getAccelY();

    double velocity = (currentAccel-prevAccelZ) * (currentTime-prevTimeZ);

    prevTimeZ = currentTime;
    prevAccelZ = currentAccel;
    
    return velocity;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    vZ = getVelocityY();
    vX = getVelocityX();
    
  }

  public double[] getVelocity() { return new double[]{vX, vZ}; }

  public ADIS16448_IMU getIMU() { return imu; }
}
