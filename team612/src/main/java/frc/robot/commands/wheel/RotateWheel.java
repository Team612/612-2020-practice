package frc.robot.commands.wheel;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Wheel;

public class RotateWheel extends CommandBase {
  
  private String initial_color;  // The first color read when command runs
  private String current_color;  // The current color read by the sensor
  private String prev_color;  // The previous value read by the sensor in execute

  private double revolution_count;  // Measure the revolution within 0 <= x <= 5
  
  private boolean has_counted_revolution = false;  // Make sure revolution is counted twice
  private int buffer_count = 0;  // The amount of times the same value is read (for buffering)
  private int buffer_size = 4;  // Amount of samples to consider a correct reading

  private boolean is_complete;

  private Wheel m_wheel;  // Local subsystem from wheel

  public RotateWheel(Wheel m_wheel) {
    this.m_wheel = m_wheel;
    addRequirements(m_wheel); // needed?
  }

  @Override
  public void initialize() {
    initial_color = m_wheel.getClosestColor();   // Assign the first sensor reading
    revolution_count = -0.5;  // Set it to -0.5 to factor in first reading
    is_complete = false;
  }

  @Override
  public void execute() {

    current_color = m_wheel.getClosestColor();  // Reassign the current sensor value

    // Prevent edge cases and invalid sensor values
    if (current_color.equals(prev_color)) {
      buffer_count++;  // Count the amount of same color values
    } else {
      buffer_count = 0;
      has_counted_revolution = false;
    }

    // Measure if the sampling size is met, to increase the revolution count
    if (buffer_count > buffer_size && current_color.equals(initial_color) && !has_counted_revolution) {
      revolution_count += 0.5;
      buffer_count = 0;
      has_counted_revolution = true;
    }

    // If somehow, we surpass 5 revolutions reset the proccess
    if (revolution_count > 5) {
      revolution_count = 0;
    }

    // Once within the range of revolutions, stop the mechanism
    if(revolution_count >= 3.0 && revolution_count <= 5.0) {
      System.out.println("STOP!!!!");
      is_complete = true;
    }

    System.out.println(initial_color);
    System.out.println(current_color);
    System.out.println(revolution_count);

    prev_color = current_color;  // Assign the previous sensor value to the current and continue


  }

  @Override
  public void end(boolean interrupted) {
    // TODO: Stop the mechanism here
  }

  @Override
  public boolean isFinished() {
    return is_complete;
  }

}
