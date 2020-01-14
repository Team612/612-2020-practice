package frc.robot.commands.wheel;


import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Wheel;


public class SpinToColor extends CommandBase {
  private int arrayValue(char[] arrayName){
    int a = -1;
    for (int i = 0; i < arrayName.length; i++ ){
        if (targetColor == colorPattern[i]){
          return i; 
        }
      }
      return a;
    }
    
  
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

    targetColor = DriverStation.getInstance().getGameSpecificMessage().charAt(0);

    int index = (arrayValue(colorPattern) + 2) % 4;
    sensorTarget = colorPattern[index];
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    
    currentColor = m_wheel.getClosestColor();  // Current sensor reading updating each loop
    if(currentColor != sensorTarget){
      //motor move at power x
      System.out.println("motor move x");
    }
    if (currentColor == sensorTarget){
      //Stop motor
      System.out.println("STOP!!!");
    }


  }

  @Override
  public void end(boolean interrupted) {
    // TODO: Stop the m2otor
  }

  @Override
  public boolean isFinished() {
    return isComplete;
  }
  
}
