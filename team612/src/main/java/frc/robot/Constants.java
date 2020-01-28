/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

//RobotMap is now constants.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    //Set the ports here. These ports are based on the practice bot as of 1/27
    public static int talon_fl_port = 14;
    public static int talon_fr_port = 15;
    public static int talon_bl_port = 0;
    public static int talon_br_port = 1;
    public static int Driver_port = 0;
    public static int Gunner_port = 1;
    //THEN, go to RobotContainer
}
