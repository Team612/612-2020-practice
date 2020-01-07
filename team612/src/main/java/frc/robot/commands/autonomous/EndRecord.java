package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class EndRecord extends CommandBase {

  public EndRecord() {
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    /* 
    Disable the current recording and save the result.
    Yes, this can be added into StartRecord, but I wanted 
    to make it easier to see whats going on the RobotContainer binding.
    */
    StartRecord.RECORDING = false;
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return true;  // Run once and finish
  }
}
