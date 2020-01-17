/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj.Timer;

import edu.wpi.first.wpilibj2.command.CommandBase;
import com.analog.adis16448.frc.ADIS16448_IMU;

public class Velocity extends CommandBase {
  private double currentTimer, initialTimer;
  ADIS16448_IMU accel = new ADIS16448_IMU(/*port*/ );
  //Accelerometer accelerometer = new Accelerometer(X);

  private double currentAccelX, initialAccelX; 
  private double currentAccelY, initialAccelY;
  private double currentAccelZ, initialAccelZ;

  private double currentVelocityZ = 0;
  private double currentVelocityY = 0;
  private double currentVelocityX = 0; 

  Timer timer = new Timer(); 

  

  public Velocity() {
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    timer.start();
    System.out.println(timer.get());
    timer.reset();

    System.out.println(accel.getAccelInstantX());
    System.out.println(accel.getAccelInstantY());
    System.out.println(accel.getAccelInstantZ());

    currentAccelX = accel.getAccelInstantX();
    currentAccelY = accel.getAccelInstantY();
    currentAccelZ = accel.getAccelInstantZ();


    currentVelocityX = ((initialAccelX + currentAccelX)/2.0)*(currentTimer - initialTimer);
    currentVelocityY = ((initialAccelY + currentAccelY)/2.0)*(currentTimer - initialTimer);
    currentVelocityZ = ((initialAccelZ + currentAccelZ)/2.0)*(currentTimer - initialTimer);


    initialAccelZ = currentAccelZ; 
    initialAccelY = currentAccelY; 
    initialAccelX = currentAccelX; 

    initialTimer = currentTimer; 
    
    

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
