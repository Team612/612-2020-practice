package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.autonomous.SampleAuto;
import frc.robot.commands.drivetrain.DefaultDrive;
import frc.robot.commands.wheel.RotateWheel;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Wheel;
import frc.robot.controls.ControlMap;

public class RobotContainer {

  private final Drivetrain m_drivetrain = new Drivetrain();
  private final DefaultDrive c_defaultdrive = new DefaultDrive(m_drivetrain);

  private final Wheel m_wheel = new Wheel();

  private final SampleAuto m_sampleauto = new SampleAuto();

  public RobotContainer() {
    configureButtonBindings();
    configureDefaultCommands();
  }

  // Put all button bindings here
  private void configureButtonBindings() {
    ControlMap.driver_button_A.whenPressed(new RotateWheel(m_wheel));
    
  }

  // Put all default commands here
  private void configureDefaultCommands() {
    m_drivetrain.setDefaultCommand(c_defaultdrive);
  }

  public Command getAutonomousCommand() {
    return m_sampleauto;
  }

}
