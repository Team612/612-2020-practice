package frc.robot;

import org.json.simple.JSONArray;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.autonomous.EndRecord;
import frc.robot.commands.autonomous.StartRecord;
import frc.robot.commands.autonomous.StartReplay;
import frc.robot.commands.drivetrain.DefaultDrive;
import frc.robot.controls.ControlMap;
import frc.robot.subsystems.Drivetrain;

public class RobotContainer {

  private final Drivetrain m_drivetrain = new Drivetrain();
  private final DefaultDrive m_defaultdrive = new DefaultDrive(m_drivetrain);

  private final StartReplay m_autocommand = new StartReplay("output.replay");

  // Variables for autonomous replay
  private double auto_voltage;
  private JSONArray auto_frames;

  public RobotContainer() {
    configureButtonBindings();
  }

  private void configureButtonBindings() {

    // TODO: Enventually replace ENABLE_RECORDING with ShuffleBoard input
    if (Constants.ENABLE_RECORDING) {
      ControlMap.driver_button_A.whenPressed(new StartRecord("output.replay"));
      ControlMap.driver_button_B.whenPressed(new EndRecord());
    }

  }

  public Command getAutonomousCommand() {
    return m_autocommand;
  }

}
