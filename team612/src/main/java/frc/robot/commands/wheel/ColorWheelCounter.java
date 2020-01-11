/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.wheel;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class colorWheelCounter extends CommandBase {

  private String initial_color;
  private String current_color;
  private double revolution_count;
  private String target_color;
  /**
   * Creates a new WheelRotate.
   */
  public colorWheelCounter() {
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    initial_color = "red";
    target_color = "green";
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    current_color = "red";

    if(current_color.equals(initial_color)){
     revolution_count += 0.5;
    }

    if(revolution_count >= 3.0 && revolution_count <= 5.0){
      //stop boi
    }
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
