/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.wheel;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.controls.ControlMap;
import frc.robot.subsystems.Wheel;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.util.Color;

public class SpinToColor extends CommandBase {
  /**
   * Creates a new SpinToColor.
   */
  public String colorVal;
  public Wheel s_wheel;

  public SpinToColor(Wheel wheel) {
    // Use addRequirements() here to declare subsystem dependencies.\
    s_wheel = wheel;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    String gameData;
gameData = DriverStation.getInstance().getGameSpecificMessage();
if(gameData.length() > 0) // if not null
{
  switch (gameData.charAt(0))
  {
    case 'B' :
      colorVal = "red";
      System.out.println("Target is Blue");
      break;
    case 'G' :
      colorVal = "yellow";
      System.out.println("Target is Green");
      break;
    case 'R' :
      colorVal = "blue";
      System.out.println("Target is Red");
      break;
    case 'Y' :
      colorVal = "green";
      System.out.println("Target is Yellow");
      break;
    default :
      //This is corrupt data
      break;
  }
} else {
  //Code for no data received yet
}
    //set motor value to x
    //make motor wait 1 second
    
    if(colorVal.equals(s_wheel.getClosestColor())) {
      //stop
      //wait 1 second before failsafe
      if(!colorVal.equals(s_wheel.getClosestColor())) {
        //set motor value to -x/4
        if(colorVal.equals(s_wheel.getClosestColor())) {
          //stop
          System.out.println("STOP!!!!!");
        }
      }
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
