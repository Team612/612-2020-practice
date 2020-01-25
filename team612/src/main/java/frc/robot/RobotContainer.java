package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.autonomous.SampleAuto;
import frc.robot.commands.drivetrain.DefaultDrive;
import frc.robot.commands.drivetrain.DefaultVelocity;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Velocity;

public class RobotContainer {

  private final Drivetrain m_drivetrain = new Drivetrain();
  private final Velocity m_velocity = new Velocity();
  private final DefaultDrive c_defaultdrive = new DefaultDrive(m_drivetrain);
  private final DefaultVelocity c_velocity = new DefaultVelocity(m_velocity);
  private final SampleAuto m_sampleauto = new SampleAuto();

  public RobotContainer() {
    configureButtonBindings();
    configureDefaultCommands();
  }

  // Put all button bindings here
  private void configureButtonBindings() {
    
  }

  // Put all default commands here
  private void configureDefaultCommands() {
    m_drivetrain.setDefaultCommand(c_defaultdrive);
    m_velocity.setDefaultCommand(c_velocity);
  }

  public Command getAutonomousCommand() {
    return m_sampleauto;
  }

}
