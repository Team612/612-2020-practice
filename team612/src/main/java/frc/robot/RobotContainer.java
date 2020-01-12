package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.autonomous.SampleAuto;
import frc.robot.commands.drivetrain.DefaultDrive;
import frc.robot.commands.wheel.RotateWheel;
import frc.robot.commands.wheel.SpinToColor;
import frc.robot.controls.ControlMap;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Wheel;

public class RobotContainer {

  // Drivetrain related subsystem and commands
  private final Drivetrain m_drivetrain = new Drivetrain();
  private final DefaultDrive c_defaultdrive = new DefaultDrive(m_drivetrain);
  
  // Color wheel subsystem and commands
  private final Wheel m_wheel = new Wheel();
  private final RotateWheel c_rotatewheel = new RotateWheel(m_wheel);
  private final SpinToColor c_spintocolor = new SpinToColor(m_wheel);

  private final SampleAuto m_sampleauto = new SampleAuto();

  public RobotContainer() {
    configureButtonBindings();
    configureDefaultCommands();
  }

  // Put all button bindings here
  private void configureButtonBindings() {
    ControlMap.gunner_button_A.whenPressed(c_rotatewheel);
    ControlMap.gunner_button_B.whenPressed(c_spintocolor);
  }

  // Put all default commands here
  private void configureDefaultCommands() {
    m_drivetrain.setDefaultCommand(c_defaultdrive);
  }

  public Command getAutonomousCommand() {
    return m_sampleauto;
  }

}
