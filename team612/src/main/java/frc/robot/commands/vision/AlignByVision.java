/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.vision;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class AlignByVision extends CommandBase {
  
  // PID constants
  private double kp = 0.0;
  private double ki = 0.0;
  private double kd = 0.0;

  private double tx;  // X-axis offset same as error
  private double tx_prev;  // Store the previous error for derivative
  private double tx_sum;  // Sum of error or offset for integral

  private double integral_limit = 0.04;  // Limit once integral is engaged

  // 3 output variables of PID
  private double proportion;
  private double derivative;
  private double integral;

  private double throttle;  // Result of PID loop, then converted to tank drive

  private NetworkTableListener listener;  // Listener object to update offset values

  public AlignByVision() {
    listener = new NetworkTableListener("Vision_Table");    
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    tx = listener.getOffset();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    // Start accumulating offset if offset is stuck at low value
    if (tx < integral_limit) {
      tx_sum += tx;
    } else {
      tx_sum = 0;
    }

    // Limit integral sum from getting to large
    tx_sum = tx_sum > 50/ki ? 50/ki : tx_sum;

    // If the offset is at 0 reset set D and I to 0
    if (tx == 0) {
      derivative = 0;
      tx_sum = 0;
    }

    // PID calculations
    proportion = tx * kp;
    derivative = (tx-tx_prev) * kd;
    integral = tx_sum * ki;

    tx_prev = tx;  // Assign the previous motor value for derivative

    throttle = proportion + integral + derivative;

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }

}
