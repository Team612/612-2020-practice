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
    //WPI shows warning here, underline m_drivetrain.

    public DefaultPhoenixDrive(PhoenixDrive m_drivetrain) {
        this.m_drivetrain = m_drivetrain;
        addRequirements(m_drivetrain);
        //Uhhh
    }


    @Override
    public void initialize() {}
    @Override
    public void execute() {
        double getYAxis = ControlMap.driver.getY(Hand.kLeft);
        double getXAxis = ControlMap.driver.getX(Hand.kRight);
        //This will set a variable for the commands below
    
        //Set joystick values and controls here, under execute.
        //All the right negative talons are multiplied by negative 1 because it's optimized for Rico.
        //The code will be changed for the competition bot.
        if (Math.abs(getYAxis) > 0.1 && Math.abs(getXAxis) < 0.1) {
            //Here, the variable "getYAxis" is used.
            // If the left joystick is being moved, but not the right, then:
            System.out.println("This is moving forward or backward");
            //Print this line is so that we can verify that the code is working.
            RobotContainer.m_drivetrain.talon_fr.set(-getYAxis);
            RobotContainer.m_drivetrain.talon_br.set(-getYAxis);
            RobotContainer.m_drivetrain.talon_fl.set(getYAxis);
            RobotContainer.m_drivetrain.talon_bl.set(getYAxis);
            //set these talons to whatever that y value is.

        } else if (Math.abs(getYAxis) > 0.1 && (getXAxis) > 0.1) {
            //If the left joystick is moving AND the right joystick is moving to the right (i.e. robot making a right), then:
            System.out.println("This is moving to the right");
            RobotContainer.m_drivetrain.talon_br.set(-0.05 * getYAxis);
            RobotContainer.m_drivetrain.talon_fr.set(-0.05 * getYAxis);
            //These talons will be at this value, not 0, to ensure A) that the Y Axis value is always a factor,
            //B) that these talons' values are always less than the other talons.
            RobotContainer.m_drivetrain.talon_bl.set((getYAxis/Math.abs(getYAxis)) * Math.abs(getXAxis));
            RobotContainer.m_drivetrain.talon_fl.set((getYAxis/Math.abs(getYAxis)) * Math.abs(getXAxis));
            //Whether the X axis is positive or negative shouldn't impact what direction a talon moves.
            //It only impacts which set of talons move. This is why there is the absolute value on the X.
            //The Y axis direction impacts which direction should move, which is whyY Axis is divided by its abs value.
            //If the Y Axis value is negative, the X Axis will move in that direction. If positive, same thing.
            //Example: moving forward right: Right talons movement: (postive/positive)*positive. Left: positive
            //Moving backward right: right: ((negative/positive)*postive). Left: negative

        } else if (Math.abs(getYAxis) > 0.1 && (getXAxis) < -0.1) {
            //If the left joystick is moving and the right is moving to the left (i.e. robot making a left):
            System.out.println("This is moving to the left");
            RobotContainer.m_drivetrain.talon_fr.set(-(getYAxis/Math.abs(getYAxis)) * Math.abs(getXAxis));
            RobotContainer.m_drivetrain.talon_br.set(-(getYAxis/Math.abs(getYAxis)) * Math.abs(getXAxis));
            RobotContainer.m_drivetrain.talon_fl.set(0.05 * getYAxis);
            RobotContainer.m_drivetrain.talon_bl.set(0.05 * getYAxis);
            //Same concept as above
            //Example: moving forward left: Right talons movement: (postive/positive)*positive. Left: positive
            //Moving backward left: right: ((negative/positive)*postive). Left: negative

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