/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.drivetrain;

import frc.robot.RobotContainer;
import frc.robot.controls.ControlMap;
import frc.robot.subsystems.PhoenixDrive;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.CommandBase;


public class DefaultPhoenixDrive extends CommandBase {

  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final PhoenixDrive m_drivetrain;

  public DefaultPhoenixDrive(PhoenixDrive m_drivetrain) {
    this.m_drivetrain = m_drivetrain;
    addRequirements(m_drivetrain);
    //Uhhh
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    //MOST IMPORTANT. Come from RobotContainer
    //Set joystick values and controls here, under execute.
    if (Math.abs(ControlMap.driver.getY(Hand.kLeft)) > 0.1) { 
    //If the left joystick's y value is not zero, then
    RobotContainer.m_drivetrain.talon_fr.set(ControlMap.driver.getY(Hand.kLeft));
    //set these talons to whatever that y value is.
    RobotContainer.m_drivetrain.talon_fl.set(ControlMap.driver.getY(Hand.kLeft));
    RobotContainer.m_drivetrain.talon_br.set(ControlMap.driver.getY(Hand.kLeft));
    RobotContainer.m_drivetrain.talon_bl.set(ControlMap.driver.getY(Hand.kLeft));
    }
    else if (ControlMap.driver.getX(Hand.kRight) > 0.1) {
      //BUT if the right joystick's x value is greater then zero, then set the following talon values accordingly
    RobotContainer.m_drivetrain.talon_fr.set(0);
    RobotContainer.m_drivetrain.talon_fl.set(ControlMap.driver.getX(Hand.kRight));
    RobotContainer.m_drivetrain.talon_br.set(0);
    RobotContainer.m_drivetrain.talon_bl.set(ControlMap.driver.getX(Hand.kRight));
    }
      else if (ControlMap.driver.getX(Hand.kRight) < -0.1) {
        //OR if the right joystick's x value is less then 0, then set the following talon values accordingly
      RobotContainer.m_drivetrain.talon_fr.set(ControlMap.driver.getX(Hand.kRight));
      RobotContainer.m_drivetrain.talon_fl.set(0);
      RobotContainer.m_drivetrain.talon_br.set(ControlMap.driver.getX(Hand.kRight));
      RobotContainer.m_drivetrain.talon_bl.set(0);
      }
      else if (Math.abs(ControlMap.driver.getY(Hand.kLeft)) < 0.1 && (Math.abs(ControlMap.driver.getX(Hand.kRight)) < 0.1)) {
        //But if the absolute values of both the Y on left and X on right is 0, then set the talon values to 0.
      RobotContainer.m_drivetrain.talon_fr.set(0);
      RobotContainer.m_drivetrain.talon_fl.set(0);
      RobotContainer.m_drivetrain.talon_br.set(0);
      RobotContainer.m_drivetrain.talon_bl.set(0);
    }
    
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }

}