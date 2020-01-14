package frc.robot.commands.autonomous;

import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.controls.ControlMap;

public class StartRecord extends CommandBase {

  public static boolean RECORDING;  // Boolean to end the function on button press
  private String DIRECTORY = "/home/lvuser/";  // Directory to ROBORIO
  private String OUTPUT_FILE;  // Output file name for movement

  // Joysticks to record from (will be replaced with control maps)
  private Joystick[] joysticks = {ControlMap.driver, ControlMap.gunner};  // Array for joysticks to be measured

  private JSONArray frames;  // Output array to pass into the file
  private double voltage;  // Store current voltage before replay

  public StartRecord(String OUTPUT_FILE) {  // Passs output file in constructor
    this.OUTPUT_FILE = OUTPUT_FILE;
  }


  @Override
  public void initialize() {
    RECORDING = true;  // Reset the value of RECORDING every time the command is called
    frames = new JSONArray();  // Reset the JSON array each call
    voltage = RobotController.getBatteryVoltage();
  }


  @Override
  public void execute() {

    JSONObject frame = capture(joysticks);  // Get the current JSON structure for this iteration
    frames.add(frame);  // Append the json frame to the array
    System.out.println(frame);

  }


  @Override
  public void end(boolean interrupted) {
    JSONObject export_json = new JSONObject();
    export_json.put("voltage", voltage);
    export_json.put("frames", frames);

    save_json(export_json, DIRECTORY + OUTPUT_FILE);  // Save the final JSONArray to directory in ROBORIO

    System.out.println(DIRECTORY + OUTPUT_FILE);
    System.out.println("Succesfully saved replay file!");

  }


  @Override
  public boolean isFinished() {
    return !RECORDING;  // Check when RECORDING = false to end
  }


  /* ----- Custom Functions Below ----- */

  private JSONObject capture(Joystick[] joysticks)  {  // Input list of Joysticks to read from

    JSONObject frame = new JSONObject();  // Final output json line for all 
    int count = 0;

    for (Joystick joystick : joysticks) {  // Iterate through each joystick

      JSONObject joy_frame = new JSONObject();  // Final JSON object for single joystick

      // JSON objects for each maps of controllers
      JSONObject buttons = new JSONObject();
      JSONObject axes = new JSONObject();
      JSONObject pov = new JSONObject();

      for (int i = 0; i < joystick.getButtonCount(); i++) {
        buttons.put(i, joystick.getRawButton(i));
      }

      for (int i = 0; i < joystick.getAxisCount(); i++) {
        axes.put(i, joystick.getRawAxis(i));
      }

      for (int i = 0; i < joystick.getPOVCount(); i++) {
        pov.put(i, joystick.getPOV(i));
      }

      // Combine the individual control groups into single joystick JSON object
      joy_frame.put("buttons", buttons);
      joy_frame.put("axes", axes);
      joy_frame.put("pov", pov);

      frame.put(count, joy_frame);  // Append the controller and its mapping to the current frame object
      count++;
      
    }
    
    return frame;  // Return the finalized single loop json object

  }


  // Input final JSON array and save it to designated file path.
  private void save_json(JSONObject json_object, String output_path) { 

    try (FileWriter file = new FileWriter(output_path)) {
      file.write(json_object.toJSONString());
      System.out.println("Yeet");
    } catch (IOException e) {
      System.out.println("Unable to save JSON file");
      end(true);  // If error end the function
    }

  }


}