package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.autonomous.SampleAuto;
import frc.robot.commands.vision.AlignByVision;
import frc.robot.controls.ControlMap;
import frc.robot.subsystems.Drivetrain;

public class RobotContainer {

  private final Drivetrain m_drivetrain = new Drivetrain();

  private final SampleAuto m_sampleauto = new SampleAuto();

  public RobotContainer() {
    configureButtonBindings();
    configureDefaultCommands();
  }

  // Put all button bindings here
  private void configureButtonBindings() {
    // Run vision alignment
    ControlMap.driver_button_LB.toggleWhenPressed(new AlignByVision(m_drivetrain));
  }

  // Put all default commands here
  private void configureDefaultCommands() {
  }

  public Command getAutonomousCommand() {
    return m_sampleauto;
  }

}
