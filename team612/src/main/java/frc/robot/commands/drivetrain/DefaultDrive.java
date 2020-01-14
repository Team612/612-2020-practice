package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.controls.ControlMap;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Drivetrain.DriveTalon;


public class DefaultDrive extends CommandBase {  
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final Drivetrain m_drivetrain;

  private final double deadzone = 0.1;
  
  public DefaultDrive(Drivetrain m_drivetrain) {
    this.m_drivetrain = m_drivetrain;
    addRequirements(m_drivetrain);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    double left = ControlMap.driver.getY(Hand.kLeft);
    double right = ControlMap.driver.getY(Hand.kRight);

    if (Math.abs(left) <= deadzone) left = 0;
    if (Math.abs(right) <= deadzone) right = 0;

    m_drivetrain.getTalon(DriveTalon.FrontLeft.value).set(left);
    m_drivetrain.getTalon(DriveTalon.BackLeft.value).set(left);

    m_drivetrain.getTalon(DriveTalon.FrontRight.value).set(right);
    m_drivetrain.getTalon(DriveTalon.FrontRight.value).set(right);
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }

}
