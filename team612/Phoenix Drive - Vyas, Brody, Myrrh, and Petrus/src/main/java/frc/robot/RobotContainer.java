/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/


//Initialize commands, subsystems, set default commands and such. Some aspects of RobotMap.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.drivetrain.DefaultPhoenixDrive;
import frc.robot.subsystems.PhoenixDrive;
import edu.wpi.first.wpilibj2.command.Command;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  //come from constants.
  // The robot's subsystems and commands are defined here...
  public static PhoenixDrive m_drivetrain = new PhoenixDrive();
  private final DefaultPhoenixDrive c_defaultdrive = new DefaultPhoenixDrive(m_drivetrain); //use m_drivetrain for drivetrain
  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    //Anoop did this.
    // Configure the button bindings
    configureButtonBindings();
    configureDefaultCommands();
  }

  public PhoenixDrive getM_drivetrain() {
    return m_drivetrain;
  }
  //Go to DefaultPhoenixDrive


  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by instantiating a {@link GenericHID} or one of its subclasses
   * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
   * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
  }

  private void configureDefaultCommands() {
    m_drivetrain.setDefaultCommand(c_defaultdrive);
    //See public RobotContainer above.
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
}
