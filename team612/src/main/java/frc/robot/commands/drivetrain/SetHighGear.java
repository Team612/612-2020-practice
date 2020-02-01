package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class SetHighGear extends CommandBase {

  // Local version of drivetrain
  private final Drivetrain m_drivetrain;

  // Add requirements of drivetrain subsystem
  public SetHighGear(Drivetrain m_drivetrain) {
    this.m_drivetrain = m_drivetrain;
    addRequirements(m_drivetrain);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute(){
   m_drivetrain.shiftForward();  // Shift the gears forward
   SmartDashboard.putString("Speed", "High Gear");
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return true;  // Only run once
  }

}
