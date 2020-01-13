package frc.robot.commands.wheel;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Wheel;

public class SpinToColor extends CommandBase {

  char[] colorPattern = {'R', 'Y', 'B', 'G'};  // Pattern of wheel colors
  
  int offset = 2;  // Color offset (actual vs. target)

  char targetColor; // Actual target value (FMS)
  char sensorTarget;  // Sensor target value (Since two colors behind on wheel) 
  char currentColor;  // Current sensor value

  boolean isComplete = false;  // Variable to end the command

  private Wheel m_wheel;  // Local subsystem from wheel

  public SpinToColor(Wheel m_wheel) {
    this.m_wheel = m_wheel;
    addRequirements(m_wheel); // needed?
  }

  @Override
  public void initialize() {
    isComplete = false;  // Reset isComplete each call
    if (DriverStation.getInstance().getGameSpecificMessage().length() > 0) {
      targetColor = DriverStation.getInstance().getGameSpecificMessage().charAt(0);  // Actual target value
      sensorTarget = getScaledTargetColor(targetColor);  // Scaled target value 2 more steps into the pattern
    } else {
      end(true);  // End the function right away if not wheel stage
    }
  }

  @Override
  public void execute() {

    currentColor = m_wheel.getClosestColor();  // Current sensor reading updating each loop

    // End the command once target value is hit
    if (currentColor == sensorTarget) {
      isComplete = true;
    }

    // TODO: Rotate at fixed rate

  }

  @Override
  public void end(boolean interrupted) {
    // TODO: Stop the motor
  }

  @Override
  public boolean isFinished() {
    return isComplete;
  }

  /* ------ Custom Functions ------ */

  // Returns index of char in array
  private int getIndex(char target) {
    for (int i = 0; i < colorPattern.length; ++i) {
      if (colorPattern[i] == target) {
        return i;
      }
    }
    return -1;
  }

  // Gets char of pattern two steps from target value
  private char getScaledTargetColor(char color) {
    int targetIndex = (getIndex(color) + 2) % 4;
    return colorPattern[targetIndex];
  }
  
}
