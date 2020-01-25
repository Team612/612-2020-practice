/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Velocity;

import com.kauailabs.navx.frc.AHRS;

public class DefaultVelocity extends CommandBase implements Sendable{
  private double currentTimer, initialTimer;
  AHRS accel = new AHRS(SPI.Port.kMXP);
  // Accelerometer accelerometer = new Accelerometer(X);

  private double currentAccelX;
  private double currentAccelY; 
  private double currentAccelZ; 

  private double strgAccelX = 0;
  private double strgAccelY = 0;
  private double strgAccelZ = 0;

  private double initialAccelX = 0;
  private double initialAccelY = 0;
  private double initialAccelZ = 0;
  private double initialVelocityX = 0; 
  private double initialVelocityY = 0;
  private double initialVelocityZ = 0;

  private double currentVelocityZ = 0;
  private double currentVelocityY = 0;
  private double currentVelocityX = 0; 

  private double displacementZ = 0;
  private double displacementY = 0;
  private double displacementX = 0; 

  private double positionX = 0;
  private double positionY = 0;
  private double positionZ = 0;

  private int loop = 0;
  private int iterationCount = 5;

  // Velocity 
  Timer timer = new Timer();
  private Velocity m_velocity;

  public DefaultVelocity(Velocity m_velocity) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.m_velocity = m_velocity;
    addRequirements(m_velocity);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    timer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    currentTimer = timer.get();

    currentAccelX = (accel.getWorldLinearAccelX() * 9.8);
    currentAccelY = (accel.getWorldLinearAccelY() * 9.8);
    currentAccelZ = (accel.getWorldLinearAccelZ() * 9.8);

    if(Math.abs(currentAccelX) < 0.2) {
      currentAccelX = 0;
    }
    if(Math.abs(currentAccelY) < 0.2) {
      currentAccelY = 0;
    }
    if(Math.abs(currentAccelZ) < 0.22) {
      currentAccelZ = 0;
    }

    strgAccelX += currentAccelX;
    strgAccelY += currentAccelY;
    strgAccelZ += currentAccelZ;

    //SmartDashboard.putNumber("StrgX", strgAccelX);
    //SmartDashboard.putNumber("StrgY", strgAccelY);
    //SmartDashboard.putNumber("StrgZ", strgAccelZ);

    loop++;
    if(loop >= iterationCount) {

    currentAccelX = (strgAccelX/iterationCount);
    currentAccelY = (strgAccelY/iterationCount);
    currentAccelZ = (strgAccelZ/iterationCount);

    loop = 0;
    strgAccelX = 0;
    strgAccelY = 0;
    strgAccelZ = 0;

    currentVelocityX = initialVelocityX + ((initialAccelX + currentAccelX)/2.0)*(currentTimer - initialTimer);
    currentVelocityY = initialVelocityY + ((initialAccelY + currentAccelY)/2.0)*(currentTimer - initialTimer);
    currentVelocityZ = initialVelocityZ + ((initialAccelZ + currentAccelZ)/2.0)*(currentTimer - initialTimer);
    
    if(Math.abs(currentAccelX) < 0.02 && Math.abs(currentVelocityX) < 0.08) {
      currentVelocityX = 0;
    }
    if(Math.abs(currentAccelY) < 0.02 && Math.abs(currentVelocityY) < 0.08) {
      currentVelocityY = 0;
    }
    if(Math.abs(currentAccelZ) < 0.02 && Math.abs(currentVelocityZ) < 0.08) {
      currentVelocityZ = 0;
    }
    /*
    System.out.println(currentVelocityX);
    System.out.println(currentVelocityY);
    System.out.println(currentVelocityZ);

    System.out.println(initialVelocityX);
    System.out.println(initialVelocityY);
    System.out.println(initialVelocityZ);

    System.out.println(displacementX);
    System.out.println(displacementY);
    System.out.println(displacementZ);
    */

    // update acceleration 
    initialAccelZ = currentAccelZ; 
    initialAccelY = currentAccelY; 
    initialAccelX = currentAccelX; 

    //displacement calculation
    displacementX = ((initialVelocityX + currentVelocityX)/2.0)*(currentTimer - initialTimer);
    displacementY = ((initialVelocityY + currentVelocityY)/2.0)*(currentTimer - initialTimer);
    displacementZ = ((initialVelocityZ + currentVelocityZ)/2.0)*(currentTimer - initialTimer);

    positionX += displacementX;
    positionY += displacementY;
    positionZ += displacementZ;

    displacementX = 0;
    displacementY = 0;
    displacementZ = 0;

    //update velocity 
    initialVelocityZ = currentVelocityZ; 
    initialVelocityY = currentVelocityY; 
    initialVelocityX = currentVelocityX;
    
    SmartDashboard.putNumber("positionX", positionX);
    SmartDashboard.putNumber("positionY", positionY);
    SmartDashboard.putNumber("positionZ", positionZ);    
    SmartDashboard.putNumber("VelocityX", currentVelocityX);
    SmartDashboard.putNumber("VelocityY", currentVelocityY);
    SmartDashboard.putNumber("VelocityZ", currentVelocityZ);
    SmartDashboard.putNumber("AccelerationX", currentAccelX);
    SmartDashboard.putNumber("AccelerationY", currentAccelY);
    SmartDashboard.putNumber("AccelerationZ", currentAccelZ);
    
    /*
    System.out.println(currentAccelX);
    System.out.println(currentAccelY);
    System.out.println(currentAccelZ);
    */

    initialTimer = currentTimer; 
    }
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
}
