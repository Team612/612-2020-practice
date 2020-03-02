package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.autonomous.SampleAuto;
import frc.robot.commands.drivetrain.DefaultDrive;
import frc.robot.commands.drivetrain.SetHighGear;
import frc.robot.commands.drivetrain.SetLowGear;
import frc.robot.controls.ControlMap;
import frc.robot.subsystems.Drivetrain;

public class RobotContainer {

  // Drive subsystem and command
  private final Drivetrain m_drivetrain = new Drivetrain();
  private final DefaultDrive c_drive = new DefaultDrive(m_drivetrain);

  private final SampleAuto m_sampleauto = new SampleAuto();

  public RobotContainer() {
    configureButtonBindings();
    configureDefaultCommands();
  }

  // Put all button bindings here
  private void configureButtonBindings() {
    ControlMap.driver_button_LB.whenPressed(new SetHighGear(m_drivetrain));
    ControlMap.driver_button_RB.whenPressed(new SetLowGear(m_drivetrain));
    ControlMap.driver_button_A.whileHeld(new )
  }

  // Put all default commands here
  private void configureDefaultCommands() {
    m_drivetrain.setDefaultCommand(c_drive);
  }

  public Command getAutonomousCommand() {
    return m_sampleauto;
  }

}
