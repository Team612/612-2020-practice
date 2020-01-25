/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/


//MOST IMPORTANT. Come from Constants


package frc.robot.commands.drivetrain;

import frc.robot.RobotContainer;
import frc.robot.controls.ControlMap;
import frc.robot.subsystems.PhoenixDrive;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.CommandBase;


public class DefaultPhoenixDrive extends CommandBase {

    @SuppressWarnings({
        "PMD.UnusedPrivateField",
        "PMD.SingularField"
    })
    private final PhoenixDrive m_drivetrain;

    public DefaultPhoenixDrive(PhoenixDrive m_drivetrain) {
        this.m_drivetrain = m_drivetrain;
        addRequirements(m_drivetrain);
        //Uhhh
    }

    double getYAxis = ControlMap.driver.getY(Hand.kLeft);
    double getXAxis = ControlMap.driver.getX(Hand.kRight);
    //This will set a variable for the commands below, under public void execute{

    @Override
    public void initialize() {}
    @Override
    public void execute() {

        //Set joystick values and controls here, under execute.
        if (Math.abs(getYAxis) > 0.1 && Math.abs(getXAxis) < 0.1) {
            // If the left joystick is being moved, but not the right, then:
            System.out.println("This is moving forward or backward");
            //Print this line is so that we can verify that the code is working.
            RobotContainer.m_drivetrain.talon_fr.set(getYAxis);
            //set these talons to whatever that y value is.
            //Here, the variable "getYAxis" is used.
            RobotContainer.m_drivetrain.talon_fl.set(getYAxis);
            RobotContainer.m_drivetrain.talon_br.set(getYAxis);
            RobotContainer.m_drivetrain.talon_bl.set(getYAxis);
            //All talons are moving with whatever the Y axis value is.
        } else if (Math.abs(getYAxis) > 0.1 && (getXAxis) > 0.1) {
            //If the left joystick is moving AND the right joystick is moving to the right (i.e. robot making a right), then:
            System.out.println("This is moving to the right");
            //Again, verifies that the code is working
            RobotContainer.m_drivetrain.talon_br.set(0.5 * getYAxis);
            RobotContainer.m_drivetrain.talon_fr.set(0.5 * getYAxis);
            //Multiply the value of the Y axis by 1/2.
            RobotContainer.m_drivetrain.talon_bl.set(getXAxis);
            RobotContainer.m_drivetrain.talon_fl.set(getXAxis);
            //This talon will be going at the X axis speed
            //This is done so that the right talons will move slower than the left ones, thus making the robot rotate right.
            //Right talons != 0 bc then robot will only go forward and right, you can't go back right.
        } else if (Math.abs(getYAxis) > 0.1 && (getXAxis) < 0.1) {
            //If the left joystick is moving and the right is moving to the left (i.e. robot making a left):
            System.out.println("This is moving to the left");
            RobotContainer.m_drivetrain.talon_fr.set(getXAxis);
            RobotContainer.m_drivetrain.talon_br.set(getXAxis);
            RobotContainer.m_drivetrain.talon_fl.set(0.5 * getYAxis);
            RobotContainer.m_drivetrain.talon_bl.set(0.5 * getYAxis);
            //Same concept as above
        } else if (Math.abs(getYAxis) < 0.1 && (Math.abs(getXAxis) < 0.1)) {
            System.out.println("This is not moving at all");
            //But if neither joystick moves, then don't move the robot.
            RobotContainer.m_drivetrain.talon_fr.set(0);
            RobotContainer.m_drivetrain.talon_fl.set(0);
            RobotContainer.m_drivetrain.talon_br.set(0);
            RobotContainer.m_drivetrain.talon_bl.set(0);
        }

    }

    @Override
    public void end(boolean interrupted) {}

    @Override
    public boolean isFinished() {
        return false;
    }

}