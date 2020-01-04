package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.autonomous.ReplayRobot;
import frc.robot.subsystems.Drivetrain;

public class RobotContainer {

  private final Drivetrain m_drivetrain = new Drivetrain();
  private final ReplayRobot m_autoCommand = new ReplayRobot();

  public RobotContainer() {
    configureButtonBindings();
  }

  private void configureButtonBindings() {
    
  }

  public Command getAutonomousCommand() {
    return m_autoCommand;
  }

}
