/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.controls.ControlMap;
import frc.robot.subsystems.Intake;

public class DefaultIntake extends CommandBase {
  /**
   * Creates a new Intake.
   */
  private final Intake m_Intake;

  public DefaultIntake(Intake m_Intake) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.m_Intake = m_Intake;
    addRequirements(m_Intake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (ControlMap.gunner_button_X.get()){

      Intake.talon_intake.set(1);
      Intake.talon_intake_belt.set(1);

    }
    else if (ControlMap.gunner_button_Y.get()){

      Intake.talon_intake.set(-1);
      Intake.talon_intake_belt.set(-1);

    }else{
      
      Intake.talon_intake.set(0);
      Intake.talon_intake_belt.set(0);


    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
