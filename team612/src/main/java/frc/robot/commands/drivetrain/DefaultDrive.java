package frc.robot.commands.drivetrain;

import frc.robot.controls.ControlMap;
import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class DefaultDrive extends CommandBase {

  // Remove PMD warnings
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

  private final Drivetrain m_drivetrain;  // Local version of drivetrain

  private final double DEADZONE = 0.05;  // Deadzone constant for joystick controllers

  // Constructor and add requirements from drivetrain subsystem
  public DefaultDrive(Drivetrain m_drivetrain) {
    this.m_drivetrain = m_drivetrain;
    addRequirements(m_drivetrain);
  }

  @Override
  public void initialize(){
  }

  @Override
  public void execute(){
    // Pass values from joystick to west coast drive with deadzone value
    m_drivetrain.westCoastDrive(ControlMap.driver.getRawAxis(1), ControlMap.driver.getRawAxis(5), DEADZONE);
  }

  @Override
  public void end(boolean interrupted){

  }

  @Override
  public boolean isFinished(){

    return false;
  }

}
