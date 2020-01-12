package frc.robot.commands.vision;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class AlignByVision extends CommandBase {
  
  // PID constants for rotation
  private double kp = 0.001;
  private double ki = 0.0;
  private double kd = 0.0;

  // PID constants for distance (Only proportional so far)
  private double kp_d = 0.009;
  
  private double tx;  // X-axis offset same as error
  private double tx_prev;  // Store the previous error for derivative
  private double tx_sum;  // Sum of error or offset for integral

  private double integral_limit = 0.04;  // Limit once integral is engaged

  // 3 output variables of PID
  private double proportion;
  private double derivative;
  private double integral;

  // Output variable of distance drive
  private double distance;
  private double distance_adjust;

  private double tx_tolerance = 0;
  private double distance_tolerance = 0;

  private double throttle;  // Result of PID loop, then converted to tank drive

  private NetworkTableListener listener;  // Listener object to update offset values

  private final Drivetrain m_drivetrain;  // Drivetrain subsystem to pass into

  private boolean ALIGNED;  // Boolean to store status of alignment

  public AlignByVision(Drivetrain m_drivetrain) {
    // Add drivetrain subsystem requirements
    this.m_drivetrain = m_drivetrain;
    addRequirements(m_drivetrain);
    // Create the listener object for vision
    listener = new NetworkTableListener("Vision_Table");  
    listener.createListeners();  
  }


  @Override
  public void initialize() {
    tx = listener.getOffset();  // Get current offset reading from camera
    // Reset Left and Right calculated motor values
    ALIGNED = false;
  }


  @Override
  public void execute() {
    /*
    https://readthedocs.org/projects/limelight/downloads/pdf/latest/
    Chapter 11: Aiming and Range at the same time.
    */

    if (listener.isTargetFound()) {

      tx = listener.getOffset();  // Get current offset reading from camera
      // Distance value

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
      throttle = throttle > 1 ? 1 : throttle < -1 ? -1 : throttle;  // Truncate throttle value between -1 and 1   

      // Get distance and apply proportional constant
      distance = m_drivetrain.ultrasonic_drive.getRangeInches();


      distance_adjust = distance * kp_d;

      // Basic print statements and tank drive apply
      System.out.println("Distance: " + distance);
      System.out.println("Distance Offset: " + distance_adjust);
      System.out.println(listener.isTargetFound());

      // Apply left and right command to drivetrain
      m_drivetrain.visionDrive(distance_adjust, throttle);

      if (tx < tx_tolerance && distance < distance_tolerance) {
        ALIGNED = true;
      }

    } else {
      m_drivetrain.visionDrive(0, 0);
    }

  }


  @Override
  public void end(boolean interrupted) {
    System.out.println("Aligned!");
    m_drivetrain.visionDrive(0, 0);
  }


  @Override
  public boolean isFinished() {
    return ALIGNED;
  }

}
