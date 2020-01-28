/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

//COME FROM CONSTANTS

//Initialize commands, subsystems, set default commands and such. Some aspects of RobotMap.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.drivetrain.DefaultPhoenixDrive;
import frc.robot.subsystems.PhoenixDrive;


public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  //For some reason, these are established as a drivetrain...
  public static PhoenixDrive m_drivetrain = new PhoenixDrive();
  private final DefaultPhoenixDrive c_defaultdrive = new DefaultPhoenixDrive(m_drivetrain);
  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    //Anoop did this.
    //Configure the button bindings
    configureButtonBindings();
    configureDefaultCommands();
  }

  public PhoenixDrive getM_drivetrain() {
    return m_drivetrain;
  }
  //NEXT, Go to ControlMap


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
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
}
