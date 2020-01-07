/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.autonomous;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.controls.ControlMap;

public class StartReplay extends CommandBase {
  

  private String DIRECTORY = "/home/lvuser/";  // Directory to ROBORIO
  private String FILENAME;  // Output file name for movement

  private boolean END_REPLAY = false;  // Boolean to end the function if neccesary

  private JSONArray frames;  // Empty parser object to fetch information
  private double recording_voltage;
  

  public StartReplay(String FILENAME) {
    JSONObject parsed = parse_json(FILENAME);
    // Extract voltage and array of steps from parsed json
    recording_voltage = (double) parsed.get("voltage");
    frames = (JSONArray) parsed.get("frames");
  }


  @Override
  public void initialize() {
  }


  @Override
  public void execute() {

    int i = 0;  // Current step in replay

    JSONObject frame = (JSONObject) frames.get(i);  // Get new frame

    if (i < frame.size()) {  // Only replay for length of array
      // Get axis values from 0th port (driver), in axes list
      double leftSide = (double) getValueFromReplay(frame, 0, "axes", ControlMap.left_y_axis);
      double rightSide = (double) getValueFromReplay(frame, 0, "axes", ControlMap.right_y_axis);

      // Factor in differing voltage in recorded vs current values
      leftSide = leftSide * getVoltageCompensation();
      rightSide = rightSide * getVoltageCompensation();

      i++;  // Increase to next frame
    }

  }


  @Override
  public void end(boolean interrupted) {
  }


  @Override
  public boolean isFinished() {
    return END_REPLAY;
  }

  /* ----- Custom Functions Below ----- */

  private JSONObject parse_json(String file_name) {

    JSONParser jsonParser = new JSONParser();

    try (FileReader reader = new FileReader(file_name)) {

      Object obj = jsonParser.parse(reader);  // Parsed object from JSON
      
      return (JSONObject) obj;

    } catch (FileNotFoundException e) {
      System.out.println("File does not exist!");
    } catch (IOException e) {
      System.out.println("Unable to read file!");
    } catch (ParseException e) {
      System.out.println("Invalid JSON format!");
    }

    END_REPLAY = true;
    return null;

  }


  // Get factor to multiply motor values to factor battery depletion
  private double getVoltageCompensation() {
    return RobotController.getBatteryVoltage() / recording_voltage;
  }

  // Function simplify otherwise sloppy parsing in JSON
  private Object getValueFromReplay(JSONObject step, int joystick_port, String type, int port) {
    return (Object) ((JSONObject) ( (JSONObject) step.get(joystick_port) ).get(type) ).get(port);
  }


}