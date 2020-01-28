package frc.robot.commands.drivetrain;

import frc.robot.controls.ControlMap;
import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.CommandBase;


public class DefaultDrive extends CommandBase {

  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final Drivetrain m_drivetrain;

  public DefaultDrive(Drivetrain m_drivetrain) {
    this.m_drivetrain = m_drivetrain;
    addRequirements(m_drivetrain);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    double getLeftAxis = ControlMap.driver.getY(Hand.kLeft) * 0.8;
    double getRightAxis = ControlMap.driver.getY(Hand.kLeft) * 0.8;
    //Drivetrain.tank_drive(getLeftAxis, getRightAxis);
    Drivetrain.talon_fl.set(getLeftAxis);
    Drivetrain.talon_bl.set(getLeftAxis);
    Drivetrain.talon_fr.set(-getRightAxis);
    Drivetrain.talon_br.set(-getRightAxis);

    }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }

}
