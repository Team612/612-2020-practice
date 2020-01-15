package frc.robot.commands.wheel;


import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Wheel;


public class SpinToColor extends CommandBase {
    
  private char[] colorPattern = {'R', 'Y', 'B', 'G'};  // Pattern of wheel colors
  
  private int offset = 2;  // Color offset (actual vs. target)

  private char targetColor; // Actual target value (FMS)
  private char sensorTarget;  // Sensor target value (Since two colors behind on wheel) 
  private char currentColor;  // Current sensor value
  private int index;

  private boolean isComplete = false;  // Variable to end the command

  private Wheel m_wheel;  // Local subsystem from wheel

  public SpinToColor(Wheel m_wheel) {
    this.m_wheel = m_wheel;
    addRequirements(m_wheel);

    if (DriverStation.getInstance().getGameSpecificMessage().length() > 0) {
      targetColor = DriverStation.getInstance().getGameSpecificMessage().charAt(0);
      index = (getIndex(colorPattern) + offset) % 4;  // Get the color two steps from FMS reading
      sensorTarget = colorPattern[index];  // Set the actual sensor color target
    } else {
      System.out.println("No color in FMS!");
      end(true);
    }
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    
    currentColor = m_wheel.getClosestColor();  // Current sensor reading updating each loop
    
    System.out.println("Reading: " + currentColor);
    System.out.println("Target: " + sensorTarget);

    if (currentColor != sensorTarget) {
      // Move motor at fixed speed if not at target
    } else {
      System.out.println("STOP!!!");
      isComplete = true;  // Once on the target value, stop the command
    }


  }

  @Override
  public void end(boolean interrupted) {
    // TODO: Stop the motor
  }

  @Override
  public boolean isFinished() {
    return isComplete;
  }

  /* ----- Custom Functions ----- */

  private int getIndex(char[] array) {
    for (int i = 0; i < array.length; i++) {
      if (targetColor == colorPattern[i]) {
        return i; 
      }
    }
    return -1;
  }
  
}
