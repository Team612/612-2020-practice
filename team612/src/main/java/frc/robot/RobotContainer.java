package frc.robot;


import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import frc.robot.commands.autonomous.SampleAuto;
import frc.robot.commands.drivetrain.DefaultDrive;
import frc.robot.subsystems.Drivetrain;

public class RobotContainer {

  private final Drivetrain m_drivetrain = new Drivetrain();
  private final DefaultDrive c_defaultdrive = new DefaultDrive(m_drivetrain);

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
  }

  public Command getAutonomousCommand() {

    RamseteCommand ramseteCommand = new RamseteCommand(m_drivetrain.getTrajectory(), m_drivetrain::getPose, 
    m_drivetrain.getRamseteController(), m_drivetrain.getSimpleMotorFeedforward(), 
    m_drivetrain.getDifferentialDriveKinematics(), m_drivetrain::getWheelspeeds, 
    m_drivetrain.getPIDController(), m_drivetrain.getPIDController(), m_drivetrain::tankDriveVolts, 
    m_drivetrain);
     
    return ramseteCommand.andThen(() -> m_drivetrain.tankDriveVolts(0, 0)); // lol
    
  }

}
